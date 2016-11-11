package webserver

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

import java.util.regex.Pattern

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class CollectionTipsSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test filtering list of maps with one predicate retrieving only one item"() {
        given:
        def input = [[id: 1, name: "Bruno", age: 24], [id: 2, name: "Aline", age: 26], [id: 3, name: "Lucas", age: 24]]
        def expected = [id: 1, name: "Bruno", age: 24]

        when: "Age == 24"
        def filtered = input.find { dev -> dev.age == 24 }

        then:
        expected == filtered
        !expected.is(filtered)
    }

    void "test filtering list of maps with multiple predicates retrieving only one item"() {
        given:
        def input = [[id: 1, name: "Bruno", age: 24], [id: 2, name: "Aline", age: 26], [id: 3, name: "Lucas", age: 24]]
        def expected = [id: 1, name: "Bruno", age: 24]

        when: "Age < 26 && Name starts with B"
        def filtered = input.find { dev -> dev.age < 26 && "B" == dev.name.charAt(0) }

        then:
        expected == filtered
        !expected.is(filtered)
    }

    void "test filtering list of maps with a single predicate"() {
        given:
        def input = [[id: 1, name: "Bruno", age: 24], [id: 2, name: "Aline", age: 26], [id: 3, name: "Lucas", age: 24]]
        def expected = [[id: 1, name: "Bruno", age: 24], [id: 3, name: "Lucas", age: 24]]

        when: "Age < 26"
        def filtered = input.findAll { dev -> dev.age < 26 }

        then:
        2 == filtered.size()
        expected == filtered
        !expected.is(filtered)
    }

    void "test filtering list of maps with multiples predicate"() {
        given:
        def input = [[id: 1, name: "Bruno", age: 24], [id: 2, name: "Aline", age: 26], [id: 3, name: "Lucas", age: 24]]
        def expected = [[id: 1, name: "Bruno", age: 24], [id: 3, name: "Lucas", age: 24]]

        when: "Age < 26 && contains letter u"
        def filtered = input.findAll { dev -> dev.age < 26 && dev.name.contains("u") }

        then:
        2 == filtered.size()
        expected == filtered
        !expected.is(filtered)
    }

    void "test checking if any content meets condition"() {
        given:
        def input = [[id: 1, name: "Bruno", age: 24], [id: 2, name: "Aline", age: 26], [id: 3, name: "Lucas", age: 24]]

        when: "One Predicate: Name == Aline"
        def exists = input.any { person -> "Aline" == person.name }

        then:
        exists

        and:
        def vowels = Pattern.compile("[aeiou]", Pattern.CASE_INSENSITIVE)

        when: "Multiples Predicates: id < 2 && contains a vowel"
        exists = input.any { person -> person.id < 3 && vowels.matcher(person.name).find() }

        then:
        exists
    }

    void "test checking if all contents meet condition"() {
        given:
        def input = [[id: 1, name: "Bruno", age: 24], [id: 2, name: "Aline", age: 26], [id: 3, name: "Lucas", age: 24]]

        when: "One Predicate: age is multiple of 2"
        def exists = input.every { person -> person.age % 2 == 0 }

        then:
        exists

        and:
        def vowels = Pattern.compile("[aeiou]", Pattern.CASE_INSENSITIVE)

        when: "Multiples Predicates: age is multiple of 2 && contains a vowel"
        exists = input.every { person -> (person.age % 2 == 0) && vowels.matcher(person.name).find() }

        then:
        exists
    }

    void "test collecting information from list"() {
        given:
        def input = [[id: 1, name: "Bruno", age: 24], [id: 2, name: "Aline", age: 26], [id: 3, name: "Lucas", age: 24]]
        def expected = [1, 2, 3]

        when: "Collecting all ids"
        def ids = input.collect { dev -> dev.id }

        then:
        3 == ids.size()
        expected == ids
        !expected.is(ids)

        and:
        def newInput = [1, 2, 3]
        def newExpected = [2, 4, 6]

        when:
        def transformed = newInput.collect { number -> number * 2 }

        then:
        3 == transformed.size()
        newExpected == transformed
        !newExpected.is(transformed)
    }

    void "test collecting information from list and turning it into a map"() {
        given:
        def input = [[id: 1, name: "Bruno", age: 24], [id: 2, name: "Aline", age: 26], [id: 3, name: "Lucas", age: 24]]
        def expected = ["person_1": "Bruno", "person_2": "Aline", "person_3": "Lucas"]

        when: "Collecting all ids"
        def devs = input.collectEntries { dev -> [("person_$dev.id".toString()) : dev.name] }

        then:
        3 == devs.size()
        expected == devs
        !expected.is(devs)
    }

    void "test combining filtering and transforming data from list into a map"() {
        given:
        def input = [[id: 1, name: "Bruno", age: 24], [id: 2, name: "Aline", age: 26], [id: 3, name: "Lucas", age: 24]]
        def expected = ["person_1": "Bruno", "person_3": "Lucas"]

        when: "Collecting all ids"
        def devs = input.findAll{ dev -> dev.age == 24 }
                        .collectEntries { dev ->
                            [("person_$dev.id".toString()) : dev.name]
                        }

        then:
        2 == devs.size()
        expected == devs
        !expected.is(devs)
    }


}
