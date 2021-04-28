import com.tekdays.TekMessage
import com.tekdays.TekUser
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class SecurityFilters {

    // TODO: this logger is not showing
    public static final Logger LOGGER = LoggerFactory.getLogger(SecurityFilters.class)

    def filters = {
        doLogin(controller: '*', action: '*') {
            before = {
                LOGGER.info("Showing SecurityFilter before requests controllerName: ${controllerName}, actionName: ${actionName}")
                if (!controllerName) {
                    return true
                }
                def allowedActions = ['show', 'index', 'login', 'register',
                                      'validate', 'dataTablesRenderer', 'apiData']
                if (!session.user && !allowedActions.contains(actionName) ||
                        !session.user && controllerName == 'tekMessage') {
                    redirect(controller: 'tekUser', action: 'login',
                            params: ['cName': controllerName,
                                     'aName': actionName,
                                     'id'   : params.id])  // Id parameter added for 'edit' or 'delete' actions
                    return false
                }
            }
        }

        messageCheck(controller: 'tekMessage', action: 'reply') {
            before = {
                LOGGER.info("Showing MessagingFilters. controllerName: ${controllerName}, actionName: ${actionName}")
                def tekMessage = TekMessage.get(params.id)
                def user = TekUser.get(session.user?.id)
                if (user == tekMessage.event?.organizer || user in tekMessage.event?.volunteers) {
                    return true
                } else {
                    redirect(controller: 'tekMessage', action: 'index', id: "${tekMessage.event.id}")
                    return false
                }
            }
        }
    }
}
