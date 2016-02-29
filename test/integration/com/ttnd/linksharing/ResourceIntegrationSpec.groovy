package com.ttnd.linksharing

import VO.RatingInfoVo
import grails.test.spock.IntegrationSpec

class ResourceIntegrationSpec extends IntegrationSpec {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
    }

    def "checking trending topics"()
    {
        setup:
        ResourceController resourceController = new ResourceController()

        when:
        List result = resourceController.showTrendingTopics()

        then:
        !result.isEmpty()
    }

    def "checking resource/show ratingInfoVo"()
    {
        setup:
        ResourceController resourceController = new ResourceController()

        when:
        RatingInfoVo ratingInfoVo = resourceController.show(1)

        then:
        !result.isEmpty()
    }
}
