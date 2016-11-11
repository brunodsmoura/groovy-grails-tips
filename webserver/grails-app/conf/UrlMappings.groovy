class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/data/handle/integer/$data"(controller: "parameterHandling", action: "handleInteger")
        "/data/handle/long/$data"(controller: "parameterHandling", action: "handleLong")
        "/data/handle/boolean/$data"(controller: "parameterHandling", action: "handleBoolean")
        "/data/handle/list/$data"(controller: "parameterHandling", action: "handleList")

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
