package com.ttnd.linksharing

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */

@Mock([User])
@TestFor(LoginController)
class LoginControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
    }

    def "testing login/index when session.user doesn't exist"() {
        when:
        controller.index()

        then:
        session.user == null
        response.contentAsString == "You must login. ~Failure~"
    }

    def "testing login/index when session.user exists"() {
        setup:
        User user = new User()
        user.save(validate: false)
        session.user = user

        when:
        controller.index()

        then:
        session.user != null
        response.forwardedUrl == "/user/index"
    }

    def "testing login/logout"() {
        when:
        controller.logout()

        then:
        session.user == null
        response.forwardedUrl == "/login/index"
    }

    def "testing login/loginHandler when user is not found"() {

        when:
        controller.loginHandler(userName, password)

        then:
        flash.error == "User not found"
        response.contentAsString == flash.error

        where:
        userName  | password
        null      | "wrongPassword"
        ""        | "wrongPassword"
        "notInDB" | null
        "notInDB" | ""
        "notInDB" | "wrongPassword"
    }

    def "testing login/loginHandler when user found is inactive"() {

        setup:
        User user = new User(userName:userName, password: password, isActive: false)
        user.save(validate: false)
        session.user = user

        when:
        controller.loginHandler(userName, password)

        then:
        flash.error == "User account is not active"
        response.contentAsString == flash.error

        where:
        userName | password
        "normal" | "defaultPassword"
    }

    def "testing login/loginHandler when user found is active"() {

        setup:
        User user = new User(userName:userName, password: password, isActive: true)
        user.save(validate: false)
        session.user = user

        when:
        controller.loginHandler(userName, password)

        then:
        session.user != null
        response.redirectedUrl == "/"

        where:
        userName | password
        "normal" | "defaultPassword"
    }

    def "testing login/register when new user is a valid user"() {
        when:
        controller.register()

        then:
        response.contentAsString == "New user added. ~SUCCESS~"
    }
}
