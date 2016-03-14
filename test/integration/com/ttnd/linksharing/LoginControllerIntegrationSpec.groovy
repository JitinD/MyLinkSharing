package com.ttnd.linksharing

import grails.test.spock.IntegrationSpec

class LoginControllerIntegrationSpec extends IntegrationSpec {

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
