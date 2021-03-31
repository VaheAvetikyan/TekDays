class SecurityFilters {
    def filters = {
        doLogin(controller: '*', action: '*') {
            before = {
                if (!controllerName)
                    return true
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
    }
}
