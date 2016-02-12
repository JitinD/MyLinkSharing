package com.ttnd.linksharing

import enums.Visibility
import enums.Seriousness
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Subscription)
class SubscriptionSpec extends Specification {

    @Unroll
    def "validating Seriousness enum #sno"() {
        setup:
        Subscription subscription = new Subscription(user: user, topic: topic, seriousness: seriousness)

        when:
        Boolean isValid = subscription.validate()

        then:
        isValid == result;

        where:
        sno | topic       | user       | seriousness              | result
        1   | new Topic() | new User() | Seriousness.VERY_SERIOUS | true
        2   | new Topic() | new User() | Seriousness.CASUAL       | true
        3   | new Topic() | new User() | Seriousness.SERIOUS      | true
        4   | null        | new User() | Seriousness.VERY_SERIOUS | false
        5   | new Topic() | null       | Seriousness.VERY_SERIOUS | false
        6   | new Topic() | new User() | ""                       | false
        7   | new Topic() | new User() | null                     | false

    }

    def "validating user is subscribed to unique topics"()
    {
        User user = new User(userName: "testUser", firstName: "test", lastName: "User", emailID: "testUser@mail.com", password: "testPassword")
        Topic topic = new Topic(name: "testTopic", visibility: Visibility.PRIVATE, createdBy: user)
        Subscription subscription = new Subscription(user: user, topic: topic, seriousness: Seriousness.SERIOUS)
        Subscription newSubscription = new Subscription(user: user, topic: topic, seriousness: Seriousness.SERIOUS)

        when:
        subscription.save(flush: true)
        newSubscription.save(flush: true)

        then:
        subscription.errors.allErrors.size() == 0
        newSubscription.errors.allErrors.size() == 1
        newSubscription.errors.getFieldError('topic')

    }



}
