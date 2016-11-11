package webserver

import com.brunodoescoding.data.Person
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class OperatorTipsSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test access to attributes/keys"() {
        given:
        Person aNullablePerson = null
        Map<String, Object> aMap = null

        when: "Accessing an attribute from a nullable instance"
        aNullablePerson.name

        then: "Receives NullPointerException"
        thrown(NullPointerException)

        when: "Rocking with Elvis"
        aNullablePerson?.name

        then: "No expection thrown"
        null == aNullablePerson?.name

        when: "Accessing a key from a nullable map"
        aMap.name

        then: "Receives NullPointerException"
        thrown(NullPointerException)

        when: "Rocking with Elvis"
        aMap?.name

        then: "No expection thrown"
        null == aMap?.name
    }

}
