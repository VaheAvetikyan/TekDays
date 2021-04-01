import org.slf4j.Logger
import org.slf4j.LoggerFactory

class SecurityFilters {

    // TODO: this logger is not showing
    public static final Logger LOGGER = LoggerFactory.getLogger(SecurityFilters.class)

    def filters = {
        doLogin(controller: '*', action: '*') {
            before = {
                LOGGER.info("Showing Security filter before requests controllerName: {}, actionName: {}", controllerName, actionName)
                if (!controllerName) {
                    return true
                } else if (controllerName == 'tekMessage') {
                    redirect(controller: 'tekUser', action: 'login',
                            params: ['cName': controllerName,
                                     'aName': 'index'])
                    return false
                }
                def allowedActions = ['show', 'index', 'login',
                                      'validate']
                if (!session.user && !allowedActions.contains(actionName)) {
                    redirect(controller: 'tekUser', action: 'login',
                            params: ['cName': controllerName,
                                     'aName': actionName,
                                     'id'   : params.id])  // Id parameter added for 'edit' or 'delete' actions
                    return false
                }
            }
        }

        // TODO: this filter is not working
        messageCheck(controller: 'tekMessage', action: 'reply') {
            before = {
                println("Showing MessagingFilters. controllerName: ${controllerName}, actionName: ${actionName}")
                def allowedActions = ['show', 'index']
                def tekMessage = TekMessage.get(params.id)
                if (!session.user && !allowedActions.contains(actionName) &&
                        (!(session.user == tekMessage.event?.organizer) || !(session.user in tekMessage.event?.volunteers))) {
                    redirect(controller: 'tekEvent', action: 'show', id: "${tekEvent.id}")
                    return false
                }
            }
        }
    }
}
