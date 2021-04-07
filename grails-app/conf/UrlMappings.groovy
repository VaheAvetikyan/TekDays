class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')

        "/events/$nickname"{
            controller = "tekEvent"
            action = "show"
        }

        "/users/$userName"{
            controller = "tekUser"
            action = "show"
        }
	}
}
