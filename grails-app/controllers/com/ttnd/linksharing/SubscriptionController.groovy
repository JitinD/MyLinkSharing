package com.ttnd.linksharing

import enums.Seriousness
import grails.converters.JSON

class SubscriptionController {

    def index() {}

    def save(Long topicId) {
        Topic topic = Topic.get(topicId)

        Subscription subscription = new Subscription(user: session.user, topic: topic)

        if (subscription.saveInstance())
            flash.message = "Subscription saved successfully"
        else
            flash.error = "Subscription could not be saved"

        redirect(controller: "login", action: "index")

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
                flash.message = "Subscription updated successfully"
                jsonResponseMap.message = "Subscription updated successfully"
            }
            else{
                flash.error = "Subscription could not be updated"
                jsonResponseMap.error = "Subscription could not be updated"
            }
        } else {
            flash.error =  "Subscription could not be found."
            jsonResponseMap.error =  "Subscription could not be found."
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
                    if (subscription.delete(flush: true))
                        flash.message = "Subscription deleted successfully."
                    else
                        flash.error = "Subscription could not be deleted"
                } else {
                    flash.error = "Subscription not found."
                }
            }
            else
                flash.error = "Creator of topic can't unsubscribe from the topic"

        } else {
            flash.error = "Either user or topic is not valid."
        }

        /* try {
             subscription.delete(flush: true)

         } catch (Exception e) {

         }*/

        redirect(controller: "login", action: "index")
    }
}
