package com.ttnd.linksharing

import grails.test.spock.IntegrationSpec

class ReadingItemControllerIntegrationSpec extends IntegrationSpec {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
    }

    def "testing readingItem/changeIsRead"()
    {
        setup:
        ReadingItemController readingItemController = new ReadingItemController()

        when:
        readingItemController.changeIsRead(1,true)

        then:
        ReadingItem.get(1).isRead

    }
}
