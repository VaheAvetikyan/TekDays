package com.tekdays

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Sponsor)
class SponsorSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test sponsor name"() {
        when:
        def sponsor = new Sponsor(name: '', website: 'https://www.test.com')
        then:
        !sponsor.validate(["name"])
    }
}
