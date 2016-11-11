package com.brunodoescoding.data

import grails.converters.JSON

/**
 * Created by brunomoura on 11/11/16.
 */
class ParameterHandlingController {

    def handleBoolean() {
        render([result: params.boolean("data")] as JSON)
    }

    def handleInteger() {
        render ([result: params.int("data")] as JSON)
    }

    def handleLong() {
        render ([result: params.long("data")] as JSON)
    }

    def handleList() {
        render ([result: params.list("data")] as JSON)
    }

}
