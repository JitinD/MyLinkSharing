package com.ttnd.linksharing

import grails.test.spock.IntegrationSpec
import sun.security.krb5.internal.LoginOptions

class LoginControllerIntegrationSpec extends IntegrationSpec {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
    }


    def "checking Top posts"()
    {
        setup:
        LoginController loginController = new LoginController()

        when:
        List result = loginController.index()

        then:
        !result.isEmpty()
    }
}
