package conf

import javax.servlet.http.HttpServletResponse

class ApplicationFilters {

    def filters = {
        all(controller:'*', action:'*') {
            before = {

                log.info "Controller : ${controllerName}, Action : ${actionName}"
                log.info params
                ((HttpServletResponse) response).setHeader("Cache-Control", "no-store, no-cache, must-revalidate")

            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }


}
