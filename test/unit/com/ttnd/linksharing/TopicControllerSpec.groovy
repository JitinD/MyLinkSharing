package com.ttnd.linksharing

import enums.Visibility
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */

@Mock([Topic, User, Subscription])
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
        flash.error == "Not a valid topic."
        response.redirectedUrl == "/"

        where:
        id << [null, -1]
    }

    def "testing topic/show when topic's visibility is public"() {

        setup:
        Topic topic = new Topic(id: 1, visibility: Visibility.PUBLIC)
        topic.save(validate: false)

        when:
        controller.show(topic.id)

        then:
        response.contentAsString == "Topic found and its public. ~SUCCESS~"
    }

    def "testing topic/show when topic's visibility is private and user is not subscribed to topic"() {

        Topic topic = new Topic(id: 1, visibility: Visibility.PRIVATE)
        topic.save(validate: false)

        User user = new User()
        user.save(validate: false)

        when:
        controller.show(topic.id)

        then:
        flash.error == "User is not subscribed to the topic."
    }

    def "testing topic/show when topic's visibility is private and user is subscribed to topic"() {

        Topic topic = new Topic(id: 1, visibility: Visibility.PRIVATE)
        topic.save(validate: false)

        User user = new User()
        user.save(validate: false)

        Subscription subscription = new Subscription(user: user, topic: topic)
        subscription.save(validate: false)

        when:
        controller.show(topic.id)

        then:
        flash.error == "User is not subscribed to the topic."
        response.redirectedUrl == "/"
    }


    def "testing topic/save when topic gets saved successfully"() {

        setup:
        User user = new User()
        session.user = user

        when:
        controller.save(topicName, visibility)
        Topic topic = Topic.findByName(topicName)

        then:
        topic != null
        flash.message == "Topic saved successfully"
        response.contentAsString == "Topic saved. ~SUCCESS~"

        where:
        topicName   | visibility
        "testTopic" | "public"
        "testTopic" | "private"

    }

    def "testing topic/save when topic could not be saved successfully"() {

        when:
        controller.save(topicName, visibility)
        Topic topic = Topic.findByName(topicName)

        then:
        topic == null
        flash.error == "Topic could not be saved"
        response.contentAsString == "Topic could not be saved. ~FAILURE~"

        where:
        topicName   | visibility
        "testTopic" | "public"
        "testTopic" | "private"
    }
}
