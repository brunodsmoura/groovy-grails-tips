package webserver

import com.brunodoescoding.data.Person
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class GroovyTruthSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test checking values"() {
        when:
        def emptyString = ""
        def aString = "hello"

        then:
        assert !emptyString
        assert aString

        when:
        def zero = 0
        def fortyTwo = 42

        then:
        assert !zero
        assert fortyTwo

        when:
        def emptyList = []
        def exampleList = [1, 3, 6]

        then:
        assert !emptyList
        assert exampleList

        when:
        def emptyIterator = emptyList.iterator()
        def exampleIterator = exampleList.iterator()

        then:
        assert !emptyIterator
        assert exampleIterator

        when:
        def emptyMap = [:]
        def mapWithValues = ['key': 'value']

        then:
        assert !emptyMap
        assert mapWithValues

        when:
        def nullableObject
        def anObject = new Person(name: 'Bruno', age: 24)

        then:
        assert !nullableObject
        assert anObject
    }

}
