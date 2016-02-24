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

    def "testing topic/save when topic name and visibility are valid"() {

        setup:
        session.user = user

        when:
        controller.save(topicName, visibility)

        then:
        flash.message == "Topic saved successfully"
        response.contentAsString == "Topic saved. ~SUCCESS~"

        where:
        topicName  | visibility
        "newTopic" | "public"
    }


    def "testing topic/save when topic name or visibility are invalid"() {

        setup:
        session.user = user

        when:
        controller.save(topicName, visibility)

        then:
        flash.error == "Topic could not be saved"
        response.contentAsString == "Topic could not be saved. ~FAILURE~"

        where:
        topicName  | visibility
        "newTopic" | ""
        "newTopic" | null
        ""         | "public"
        null       | "public"
        "newTopic" | "abcd"

    }
}
