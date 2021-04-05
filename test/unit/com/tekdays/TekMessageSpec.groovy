package com.tekdays

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(TekMessage)
class TekMessageSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test message"() {
        when: "you insert a valid message"
        def message = new TekMessage(subject: "hello",
                content: "hello",
                event: [name: "event"] as TekEvent,
                author: [fullname: "John Terry"] as TekUser)

        then: "the message should save"
        message.save()
    }

    void "test author false"() {
        when: "a message does not have an author, or author is null"
        def message = new TekMessage(subject: "hello",
                content: "hello")
        def message1 = new TekMessage(subject: "hello",
                content: "hello",
                author: null)

        then: "the message should fail to validate"
        !message.validate(['author'])
        !message1.validate(['author'])
    }

    void "test author true"() {
        when: "a message has an author"
        def message = new TekMessage(author: [fullname: "John Terry"] as TekUser)

        then: "the message should save"
        message.validate(['author'])
    }

    void "test event"() {
        when: "a message has an author"
        def message = new TekMessage(event: [name: "event"] as TekEvent)

        then: "the message should save"
        message.validate(['event'])
    }
}
