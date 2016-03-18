package com.ttnd.linksharing

import enums.Seriousness
import grails.converters.JSON

class SubscriptionController {

    def save(Long topicId) {
        Topic topic = Topic.get(topicId)

        Subscription subscription = new Subscription(user: session.user, topic: topic)

        if (subscription.saveInstance())
            flash.message = g.message(code: "is.saved.subscription")
        else
            flash.error = g.message(code: "not.saved.subscription")

        redirect(url: request.getHeader('referer'))

    }

    def update(Long topicId, String serious) {
        Topic topic = Topic.get(topicId)
        User user = session.user
        Map jsonResponseMap = [:]

        Subscription subscription = Subscription.findByUserAndTopic(user, topic)
        Seriousness seriousness = Seriousness.getSeriousness(serious)

        if (subscription && seriousness) {
            subscription.seriousness = seriousness

            if (subscription.saveInstance()){
                jsonResponseMap.message = g.message(code: "is.updated.subscription")
            }
            else{
                jsonResponseMap.error = g.message(code: "not.updated.subscription")
            }
        } else {
            jsonResponseMap.error =  g.message(code: "not.found.subscription")
        }

        JSON jsonResponse = jsonResponseMap as JSON
        render jsonResponse
    }

    def delete(Long topicId) {
        User user = session.user
        Topic topic = Topic.get(topicId)
        Subscription subscription = null

        if (user && topic) {

            if (!user.equals(topic.createdBy)) {

                subscription = Subscription.findByUserAndTopic(user, topic)

                if (subscription) {
                    subscription.delete(flush: true)
                    flash.message = g.message(code: "is.deleted.subscription")
                } else {
                    flash.error = g.message(code: "not.found.subscription")
                }
            }
            else
                flash.error = "Creator of topic can't unsubscribe from the topic"

        } else {
            flash.error = "Either user or topic is not valid."
        }

        redirect(controller: "login", action: "index")
    }
}
