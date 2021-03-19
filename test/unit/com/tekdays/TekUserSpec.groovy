package com.tekdays

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(TekUser)
class TekUserSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test email validation"() {
        when: 'you instantiate a user with an invalid email'
        def tekUser = new TekUser(fullName: 'Tek User',
                userName: 'tekUser',
                password: 'test15',
                email: 'email',
                website: 'https://www.test.com',
                bio: 'test')

        then: 'user email is not valid'
        !tekUser.validate(['email'])
    }

    void "test email nullable constraint"() {
        when: 'you instantiate a user without an email'
        def tekUser = new TekUser(fullName: 'Tek User',
                userName: 'tekUser',
                password: 'test15',
                website: 'https://www.test.com',
                bio: 'test')

        then: 'user email is not valid'
        !tekUser.validate(['email'])

        and: 'you can not save the user'
        !tekUser.save()
    }

    void "test username nullable constraint"() {
        when: 'you instantiate a user without a username'
        def tekUser = new TekUser(fullName: 'Tek User',
                password: 'test15',
                email: 'info@tekuser.com',
                website: 'https://www.test.com',
                bio: 'test')

        then: 'username is not valid'
        !tekUser.validate(['userName'])

        and: 'you can not save the user'
        !tekUser.save()
    }

    void "test website url constraint"() {
        when: 'you instantiate a user with invalid url'
        def tekUser = new TekUser(fullName: 'Tek User',
                userName: 'tekUser',
                password: 'test15',
                email: 'info@tekuser.com',
                website: 'test.com',
                bio: 'test')

        then: 'username is valid but website invalid'
        tekUser.validate(['userName'])
        !tekUser.validate(['website'])

        and: 'you can not save the user'
        !tekUser.save()
    }

    void "test password not blank"() {
        when: 'you instantiate a user with blank password'
        def tekUser = new TekUser(fullName: 'Tek User',
                userName: 'tekUser',
                password: '',
                email: 'info@tekuser.com',
                website: 'https://test.com',
                bio: 'test')

        then: 'username is valid but password invalid'
        tekUser.validate(['userName'])
        !tekUser.validate(['password'])

        and: 'you can not save the user'
        !tekUser.save()
    }

    void "test password length"() {
        when: 'you instantiate 2 users with one password shorter than 5 chars'
        def tekUser = new TekUser(fullName: 'Tek User',
                userName: 'tekUser',
                password: 'test15',
                email: 'info@tekuser.com',
                website: 'https://test.com',
                bio: 'test')

        def otherUser = new TekUser(fullName: 'Other User',
                userName: 'otherUser',
                password: 'test',
                email: 'info@tekuser.com',
                website: 'https://test.com',
                bio: 'test')

        then: '1st user password is valid but 2nd user password invalid'
        tekUser.validate(['password'])
        otherUser.validate(['fullName'])
        !otherUser.validate(['password'])

        and: 'you can not save 2nd user'
        !otherUser.save()
    }
}
