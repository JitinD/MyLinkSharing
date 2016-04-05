class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

       "/"(controller: "login", action: "index")

        //"/"(view:"/sample")
        //"500"(view:'/error')
        "404"(view: 'error404')
        "500"(view: "error500")

        "/login/$action?"(controller: "login")
        "/logout/$action?"(controller: "logout")

    }
}
