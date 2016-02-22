package conf

class SessionCheckFilters {

    def filters = {
        loginCheck(controller: 'login', invert: true) {
            before = {

                if(!session.user)
                    redirect(controller: "login", action: "index")

            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }
}
