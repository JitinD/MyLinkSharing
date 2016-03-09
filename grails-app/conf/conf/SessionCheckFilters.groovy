package conf

class SessionCheckFilters {

    /*def restrictAccess(User use) {
        if (user)
            redirect(controller: "login", action: "index")
    }*/

    def filters = {
        loginCheck(controller: '*', action: 'save|delete|update|changeIsRead') {
            before = {
                if (!session.user)
                    redirect(controller: "login", action: "index")
            }

        }

        userIndexcheck(controller: 'user', action: 'index') {
            before = {

                if (!session.user)
                    redirect(controller: "login", action: "index")
            }
        }
    }
}
