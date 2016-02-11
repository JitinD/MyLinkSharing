package com.ttnd.linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
    }

    def "EmailID validated when"()
    {
        setup:
        User user = new User(userName: userName, firstName: firstName, lastName: lastName, emailID: emailID, password: password);

        when:
        Boolean result = user.validate()

        then:
        result == valid

        where:

        userName    |   firstName   |   lastName    |   emailID     |   password    |   valid
        ""          |   ""          |   ""          |   ""          |   ""          |   false
        "JitinD"    |   "Jitin"     |   "Dominic"   |   "jitin.dom" |   "sdfasfsdf" |   false
        "JitinD"    |   "Jitin"     |   "Dominic"   |   "jit@m.com" |   "abcdefgh"  |   true

    }


    def "Email address should be unique"()
    {
        setup:
        String emailID = "jitin.dominic@gmail.com"
        String password = '12345678'
        User user = new User(firstName: "Jitin", lastName: "Dominic", emailID: emailID, password: password)

        when:
        user.save()

        then:
        User.count() == 1

        when:
        User newUser = new User(firstName: "Neha", lastName: "Gupta", emailID: emailID, password: password)
        newUser.save()

        then:
        User.count() == 1
        newUser.errors.allErrors.size() == 1
        newUser.errors.getFieldErrorCount('email') == 1
    }

}
