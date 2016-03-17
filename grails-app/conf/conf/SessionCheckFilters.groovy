package conf

class SessionCheckFilters {


    def filters = {
        loginCheck(controller: '*', action: 'save|delete|update|changeIsRead|validateTopicNameForSessionUser') {
            before = {
                if (!session.user)
                    redirect(controller: "login", action: "index")
            }

        }

        userIndexcheck(controller: 'user', action: 'index|toggleActive|edit|save|updatePassword|join') {
            before = {

                if (!session.user)
                    redirect(controller: "login", action: "index")
            }
        }


        consoleCheck(controller: "console", action: "*"){
            before = {

                if(!(session.user?.isAdmin))
                    redirect(controller: "login", action: "index")
            }
        }

    }

}
