package com.ttnd.linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(TopicController)
class TopicControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
    }

    def "testing topic/show when topic doesn't exist"() {

        when:
        controller.show(id)

        then:
        flash.error == "User is not subscribed to the topic."
        response.redirectedUrl == "login/index"

        where:
        id << [null, 100]
    }

    def "testing topic/show when topic's visibility is public"() {

        when:
        controller.show(id)

        then:
        response.contentAsString == "Topic found and its public. ~SUCCESS~"

        where:
        id << [1, 2, 3]
    }

    def "testing topic/show when topic's visibility is private and user is not subscribed to topic"() {

        when:
        controller.show(id)

        then:
        response.contentAsString == "User is subscribed to the topic. ~SUCCESS~"

        where:
        id << [1, 2, 3]
    }

    def "testing topic/show when topic's visibility is private and user is subscribed to topic"() {

        when:
        controller.show(id)

        then:
        flash.error == "User is not subscribed to the topic."
        response.redirectedUrl == "login/index"

        where:
        id << [1, 2, 3]
    }
}
