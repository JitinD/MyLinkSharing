package com.ttnd.linksharing

import enums.Seriousness

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
        Subscription subscription = Subscription.get(topicId)

        Seriousness seriousness = Seriousness.getSeriousness(serious)

        if (subscription && seriousness) {
            subscription.seriousness = seriousness

            if (subscription.saveInstance())
                render "Subscription updated successfully"
            else
                render "Subscription could not be updated"
        } else {
            render "Subscription with id ${id} could not be found."
        }
    }

    def delete(Long topicId) {
        User user = session.user
        Topic topic = Topic.get(topicId)
        Subscription subscription = null

        if (user && topic) {
            subscription = Subscription.findByUserAndTopic(user, topic)

            if (subscription) {
                if (subscription.delete(flush: true))
                    flash.message = "Subscription deleted successfully."
                else
                    flash.error = "Subscription could not be deleted"
            } else {
                flash.error = "Subscription not found."
            }

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
