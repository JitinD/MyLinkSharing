package com.ttnd.linksharing

import enums.Seriousness
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */

@Mock([User, Topic, Subscription])
@TestFor(SubscriptionController)
class SubscriptionControllerSpec extends Specification {

    def "testing subscription/save when subscription gets saved successfully"() {

        setup:
        Topic topic = new Topic(id: 1)
        topic.save(validate: false)

        User user = new User(id: 1)
        user.save(validate: false)

        session.user = user

        when:
        controller.save(topic.id)
        Subscription subscription = Subscription.findByUserAndTopic(user, topic)

        then:
        subscription != null
        response.contentAsString == "Subscription saved successfully"

    }

    def "testing subscription/save when subscription could not be saved successfully"() {

        setup:
        Topic topic = new Topic(id: 1)
        topic.save(validate: false)

        User user = new User(id: 1)
        user.save(validate: false)


        when:
        controller.save(topic.id)
        Subscription subscription = Subscription.findByUserAndTopic(user, topic)

        then:
        subscription == null
        response.contentAsString == "Subscription could not be saved"
    }

    def "testing subscription/update when subscription found"() {
        setup:
        Subscription subscription = new Subscription(id: 1)
        subscription.save(validate: false)

        when:
        controller.update(subscription.id, "serious")

        then:
        response.contentAsString == "Subscription updated successfully"

    }

    def "testing subscription/update when subscription not found"() {

        setup:
        Subscription subscription = new Subscription(id: 1, seriousness: Seriousness.VERY_SERIOUS)
        subscription.save(validate: false)

        when:
        controller.update(0, "serious")

        then:
        response.contentAsString == "Subscription with id 0 could not be found."


    }

    def "testing subscription/delete when id is valid"() {

        setup:
        Subscription subscription = new Subscription(id: 1, seriousness: Seriousness.VERY_SERIOUS)
        subscription.save(validate: false)

        when:
        controller.delete(subscription.id)

        then:
        noExceptionThrown()
        response.contentAsString == "Subscription deleted successfully."
    }

    def "testing subscription/delete when id is invalid"() {
        setup:
        Subscription subscription = new Subscription(id: 1, seriousness: Seriousness.VERY_SERIOUS)
        subscription.save(validate: false)

        when:
        controller.delete(0)

        then:
        Exception e = thrown(Exception)
        flash.message == "Subscription could not be deleted"
        response.contentAsString == e.message
    }


}
