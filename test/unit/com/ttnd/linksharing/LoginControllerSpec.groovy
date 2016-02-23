package com.ttnd.linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
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
        response.forwardedUrl == "login/index"
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

        when:
        controller.loginHandler(userName, password)

        then:
        session.user != null
        response.redirectedUrl = "login/index"

        where:
        userName | password
        "normal" | "defaultPassword"
    }

}
