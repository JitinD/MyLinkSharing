package com.ttnd.linksharing

import grails.test.spock.IntegrationSpec

class ReadingItemControllerIntegrationSpec extends IntegrationSpec {

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
