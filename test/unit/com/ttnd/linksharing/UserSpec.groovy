package com.ttnd.linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserSpec extends Specification {

    @Unroll
    def "validating User basic constraints related to blank, nullable and size : #sno"() {
        setup:
        User user = new User(userName: userName, firstName: firstName, lastName: lastName, emailID: emailID, password: password);

        when:
        Boolean isValid = user.validate()

        then:
        isValid == result

        where:

        sno | userName       | firstName       | lastName       | emailID             | password       | isAdmin | isActive | photo         | result
        1   | ""             | "testFirstName" | "testLastName" | "testUser@mail.com" | "testPassword" | true    | true     | "photo".bytes | false
        2   | null           | "testFirstName" | "testLastName" | "testUser@mail.com" | "testPassword" | true    | true     | "photo".bytes | false
        3   | "testUserName" | ""              | "testLastName" | "testUser@mail.com" | "testPassword" | true    | true     | "photo".bytes | false
        4   | "testUserName" | null            | "testLastName" | "testUser@mail.com" | "testPassword" | true    | true     | "photo".bytes | false
        5   | "testUserName" | "testFirstName" | ""             | "testUser@mail.com" | "testPassword" | true    | true     | "photo".bytes | false
        6   | "testUserName" | "testFirstName" | null           | "testUser@mail.com" | "testPassword" | true    | true     | "photo".bytes | false
        7   | "testUserName" | "testFirstName" | "testLastName" | ""                  | "testPassword" | true    | true     | "photo".bytes | false
        8   | "testUserName" | "testFirstName" | "testLastName" | null                | "testPassword" | true    | true     | "photo".bytes | false
        9   | "testUserName" | "testFirstName" | "testLastName" | "testUser@mail.com" | ""             | true    | true     | "photo".bytes | false
        10  | "testUserName" | "testFirstName" | "testLastName" | "testUser@mail.com" | null           | true    | true     | "photo".bytes | false
        11  | "testUserName" | "testFirstName" | "testLastName" | "testUser@mail.com" | "testPassword" | false   | true     | "photo".bytes | true
        12  | "testUserName" | "testFirstName" | "testLastName" | "testUser@mail.com" | "testPassword" | true    | true     | "photo".bytes | true
        13  | "testUserName" | "testFirstName" | "testLastName" | "testUser@mail.com" | "testPassword" | null    | true     | "photo".bytes | true
        14  | "testUserName" | "testFirstName" | "testLastName" | "testUser@mail.com" | "testPassword" | true    | false    | "photo".bytes | true
        15  | "testUserName" | "testFirstName" | "testLastName" | "testUser@mail.com" | "testPassword" | true    | true     | "photo".bytes | true
        16  | "testUserName" | "testFirstName" | "testLastName" | "testUser@mail.com" | "testPassword" | true    | null     | "photo".bytes | true
        17  | "testUserName" | "testFirstName" | "testLastName" | "testUser@mail.com" | "testPassword" | true    | true     | "".bytes      | true
        18  | "testUserName" | "testFirstName" | "testLastName" | "testUser@mail.com" | "testPassword" | true    | true     | null          | true
        19  | "testUserName" | "testFirstName" | "testLastName" | "testUsermail.com"  | "testPassword" | true    | true     | "photo".bytes | false
        20  | "testUserName" | "testFirstName" | "testLastName" | "testUser@mail.com" | "test"         | true    | true     | "photo".bytes | false
        21  | "testUserName" | "testFirstName" | "testLastName" | "testUser@mail.com" | "testPassword" | true    | true     | "photo".bytes | true

    }


    def "validating email address is unique for users"() {

        setup:
        User user = new User(userName: "testUser1", firstName: "test", lastName: "user", emailID: "testUser@mail.com", password: "testPassword")
        User newUser = new User(userName: "testUser1", firstName: "test", lastName: "user", emailID: "testUser@mail.com", password: "testPassword")

        when:
        user.save(flush: true)
        newUser.save(flush: true)

        then:
        user.errors.allErrors.size() == 0
        newUser.errors.allErrors.size() == 1
        newUser.errors.getFieldErrorCount('emailID')
    }

    def "validating full name of user"() {
        setup:
        User user = new User(firstName: firstName, lastName: lastName);

        expect:
        user.getName() == result

        where:

        firstName | lastName  | result
        ""        | ""        | ""
        "Jitin"   | "Dominic" | "Jitin Dominic"
        null      | null      | ""

    }

    def "validating toString of user"() {
        setup:
        User user = new User(userName: userName)

        when:
        String name = user.toString()

        then:
        noExceptionThrown()
        name == result

        where:
        userName       | result
        "testUserName" | "testUserName"
    }
}
