package com.brunodoescoding.data.persistence

import com.brunodoescoding.data.Person

/**
 * Created by brunomoura on 11/11/16.
 */
class PersonPersistenceService {
    def save(Person newPerson) {
        newPerson?.save()
    }
}
