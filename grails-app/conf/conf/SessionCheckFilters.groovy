package conf

class SessionCheckFilters {

    def filters = {
        loginCheck(controller: 'login|console', invert: true) {
            before = {

                if(!session.user)
                    redirect(controller: "login", action: "index")
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }


        /*loginCheck(controller: '*', action: 'save|delete|update|changeIsRead') {
            before = {

                if(!session.user)
                    redirect(controller: "login", action: "index")
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }*/
    }
}
