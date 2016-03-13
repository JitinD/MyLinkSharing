package com.ttnd.linksharing

import enums.Visibility
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Topic)
class TopicSpec extends Specification {

    def "validate topic name is unique per user"() {
        setup:

        User user = new User(userName: "testUser", firstName: "test", lastName: "User", emailID: "testUser@mail.com", password: "testPassword")
        Topic topic = new Topic(name: "testTopic", visibility: Visibility.PRIVATE, createdBy: user)
        Topic newTopic = new Topic(name: "testTopic", visibility: Visibility.PRIVATE, createdBy: user)

        when:
        topic.save(flush: true)
        newTopic.save(flush: true)

        then:
        topic.errors.allErrors.size() == 0
        newTopic.errors.allErrors.size() == 1
        newTopic.errors.getFieldErrorCount('name')

    }

    @Unroll
    def "validating Topic basic constraints related to blank, nullable and enum : #sno"() {

        setup:

        //User user = new User(userName: "JitinD", firstName: "Jitin", lastName: "Dominic", emailID: 'jitin.dominic@gmail.com', password: '123456')
        Topic topic = new Topic(name: topicName, visibility: visibility, createdBy: newUser)

        when:
        Boolean isValid = topic.validate()

        then:
        isValid == result

        where:
        sno | topicName   | visibility         | newUser    | result
        1   | ""          | Visibility.PUBLIC  | new User() | false
        2   | null        | Visibility.PUBLIC  | new User() | false
        3   | "testTopic" | ""                 | new User() | false
        4   | "testTopic" | null               | new User() | false
        5   | "testTopic" | Visibility.PUBLIC  | null       | false
        6   | "testTopic" | Visibility.PRIVATE | new User() | true
    }

    def "validating toString of topic"() {
        setup:
        Topic topic = new Topic(name: name)

        when:
        String topicName = topic.toString()

        then:
        noExceptionThrown()
        topicName == result

        where:
        name            | result
        "testTopicName" | "testTopicName"
    }
}
