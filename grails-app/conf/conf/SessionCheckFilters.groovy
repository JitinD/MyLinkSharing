package conf

class SessionCheckFilters {


    def filters = {
        loginCheck(controller: '*', action: 'save|delete|update|changeIsRead|join') {
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


        /*consoleCheck(controller: "console", action: "*"){
            before = {

                    redirect(controller: "login", action: "index")
            }
        }*/

    }

}
