class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

       "/"(controller: "login", action: "index")

        //"/"(view:"/sample")
        "500"(view:'/error')
	}
}
