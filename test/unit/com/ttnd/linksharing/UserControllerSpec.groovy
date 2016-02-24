package com.ttnd.linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(UserController)
class UserControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
    }

    def "validating user/index"()
    {
        setup:
        User user = new User(userName: "testUser")
        session.user = user

        when:
        controller.index()

        then:
        response.contentAsString == "Dashboard of user : testUser"
    }
}
