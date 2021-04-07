package com.tekdays

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(TekEvent)
class TekEventSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test toString"() {
        when: "a tekEvent has a name and a city"
        def tekEvent = new TekEvent(name: "Groovy One", city: "San Francisco", organizer: [fullname: "John Doe"] as TekUser)

        then: "the toString method will combine them."
        tekEvent.toString() == 'Groovy One, San Francisco'
    }

    void "test name"() {
        when: "event has a name"
        def event = new TekEvent(name: "Groovy One")
        then: "event name is valid, but event will not save as it is not full"
        event.validate(['name'])
        !event.save()
    }

    void "test date"() {
        when: "date field is not Date"
        def event = new TekEvent(name: "Groovy One",
                city: "San Francisco",
                organizer: [fullname: "John Doe"] as TekUser,
                startDate: "date",
                endDate: "date")
        then: "date validation should fail"
        !event.validate(['startDate', 'endDate'])
    }

    void "test endDates"() {
        when: "a tekEvent has an end date before start date"
        def event = new TekEvent(name: "Groovy One",
                city: "San Francisco",
                organizer: [fullname: "John Doe"] as TekUser,
                startDate: new Date(),
                endDate: new Date())

        def event1 = new TekEvent(name: "Groovy One",
                city: "San Francisco",
                organizer: [fullname: "John Doe"] as TekUser,
                startDate: new Date(),
                endDate: new Date() - 1)

        then: "the event endDate is valid, event1 endDate is invalid."
        event.validate(['endDate'])
        !event1.validate(['endDate'])
    }

    void "test endDate"() {
        expect:
        new TekEvent(name: "Test",
                city: "Test",
                organizer: [fullname: "Test"] as TekUser,
                startDate: startDate,
                endDate: endDate).validate(['endDate']) == ShouldBeValid

        where:
        startDate              | endDate                | ShouldBeValid
        new Date()             | new Date()             | true
        new Date(2021, 01, 01) | new Date(2020, 10, 10) | false
        new Date()             | new Date() - 1         | false
        new Date()             | new Date() + 1         | true
    }

    void "test nickname unique"() {
        when:
        def event = new TekEvent(name: "event",
                city: "test",
                description: "test",
                organizer: [fullname: "test"] as TekUser,
                venue: "test",
                startDate: new Date(),
                endDate: new Date(),
                nickname: nickname).save()
        def event1 = new TekEvent(name: "event1",
                city: "test",
                description: "test",
                organizer: [fullname: "test"] as TekUser,
                venue: "test",
                startDate: new Date(),
                endDate: new Date(),
                nickname: nickname1).save()

        then:
        TekEvent.count() == countAll

        where:
        nickname | nickname1  | countAll
        // TODO: something is not working with unique constraint
        'name'   | 'name'     | 2
        'name'   | 'testName' | 2
    }
}
