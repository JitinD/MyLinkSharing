package conf

class ApplicationFilters {

    def filters = {
        all(controller:'*', action:'*') {
            before = {

                log.info "Controller : ${controllerName}, Action : ${actionName}"
                log.info params

            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }


}
