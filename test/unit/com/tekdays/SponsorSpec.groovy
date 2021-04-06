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

    void "test name"() {
        expect:
        new Sponsor(name: sponsorName).validate(['name']) == shouldBe

        where:
        sponsorName | shouldBe
        ''          | false
        'sponsor'   | true
        null        | false
        "Test"      | true
    }

    void "test website"() {
        expect:
        new Sponsor(website: sponsorWebsite).validate(['website']) == shouldBe

        where:
        sponsorWebsite            | shouldBe
        ''                        | false
        'sponsor.com'             | false
        null                      | false
        "www.sponsor.com"         | false
        "http://www.sponsor.com"  | true
        "https://www.sponsor.com" | true
    }
}
