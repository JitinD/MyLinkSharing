package conf

class ApplicationFilters {

    def filters = {
        all(controller:'*', action:'*') {
            before = {

                log.info ""

            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }

        loginCheck(controller: "login", invert: true){
            before = {
                if(!session.user){
                    redirect(controller: "login", action: "index")
                }

            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }


}
