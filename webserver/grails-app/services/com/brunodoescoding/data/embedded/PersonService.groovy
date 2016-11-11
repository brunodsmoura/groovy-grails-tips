package com.brunodoescoding.data.embedded

import com.brunodoescoding.data.Person
import grails.transaction.Transactional

class PersonService {

    @Transactional(readOnly = true)
    def list() {
        Person.list(max: 10)
    }

    def get(Long id) {
        Person.get(id)
    }

    @Transactional
    def save(Person newPerson) {
        newPerson?.save()
    }

}
