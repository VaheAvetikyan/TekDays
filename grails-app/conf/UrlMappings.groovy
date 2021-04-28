class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?" {
            constraints {
                // apply constraints here
            }
        }

        "/"(view: "/index")
        "500"(view: '/error')

        "/api/$controller?/$id?" {
            action = "apiData"
        }

        "/events/$nickname" {
            controller = "tekEvent"
            action = "show"
        }

        "/users/$userName" {
            controller = "tekUser"
            action = "show"
        }
    }
}
