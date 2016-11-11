package webserver

import com.brunodoescoding.data.ParameterHandlingController
import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import groovy.json.JsonSlurper
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
@TestFor(ParameterHandlingController)
class ParameterTipsSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test parsing integer"() {
        when:
        params.data = data
        controller.handleInteger()

        then:
        response.text
        result == new JsonSlurper().parseText(response.text).result

        where:
        data                    |   result
        "0"                     |   0
        "INVALID"               |   null
        "$Integer.MAX_VALUE"    |   Integer.MAX_VALUE
    }

    void "test parsing long"() {
        when:
        params.data = data
        controller.handleLong()

        then:
        response.text
        result == new JsonSlurper().parseText(response.text).result

        where:
        data                    |   result
        "0"                     |   0
        "INVALID"               |   null
        "$Long.MAX_VALUE"       |   Long.MAX_VALUE
    }

    void "test parsing boolean"() {
        when:
        params.data = data
        controller.handleBoolean()

        then:
        response.text
        result == new JsonSlurper().parseText(response.text).result

        where:
        data                    |   result
        "0"                     |   false
        "INVALID"               |   false
        "true"                  |   true
        1                       |   true
    }

    void "test parsing list"() {
        when:
        params.data = data
        controller.handleList()

        then:
        response.text
        result == new JsonSlurper().parseText(response.text).result

        where:
        data                    |   result
        [1,2,3]                 |   [1,2,3]
        "a"                     |   ["a"]
        "null"                  |   ["null"]
        ""                      |   [""]
    }

}
