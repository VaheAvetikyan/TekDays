package com.tekdays

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Task)
class TaskSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test title"() {
        when:
        def task1 = new Task(title: 'to do',
                event: new TekEvent(name: "Groovy One",
                        city: "San Francisco",
                        organizer: [fullname: "John Doe"] as TekUser))
        def task2 = new Task(title: '')

        then:
        task1.save()
        !task2.validate(['title'])
    }

    void "test task"() {
        expect:
        new Task(title: title, notes: note, assignedTo: user).validate(['title', 'note', 'user']) == valid

        where:
        title  | note          | user                          | valid
        'test' | 'test'        | [fullName: 'user'] as TekUser | true
        'test' | ''            | [fullName: 'user'] as TekUser | true
        'test' | null          | null                          | true
        ''     | 'test'        | [fullName: 'user'] as TekUser | false
    }
}
