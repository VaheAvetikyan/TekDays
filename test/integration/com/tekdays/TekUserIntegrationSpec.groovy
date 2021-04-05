package com.tekdays

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin}
 * for usage instructions
 */
@TestFor(TekUser)
class TekUserIntegrationSpec extends Specification {
    void setup() {
        new TekUser(fullName: "Thierry Henry",
                userName: "thierry",
                password: "french",
                email: "henry@gmail.com",
                website: "https://www.thieryhenry.com",
                bio: "French football player").save()
        new TekUser(fullName: 'Wayne Rooney',
                userName: 'rooney',
                password: 't0ps3cr3t',
                email: 'rooney@gmail.com',
                website: 'https://blog.rooney.com',
                bio: "Wayne Rooney is an English football player.").save()
    }

    void "test user count"() {
        expect: "Count of users is 2, and count by names 1"
        TekUser.count() == 2
        TekUser.countByFullName('Thierry Henry') == 1
        TekUser.countByFullName('Wayne Rooney') == 1
    }

    void "test retrieving user"() {
        expect:
        TekUser.get(1).fullName == 'Thierry Henry'
        TekUser.findByUserName("rooney").id == 2
        TekUser.findByEmail('henry@gmail.com').id == 1
    }
}
