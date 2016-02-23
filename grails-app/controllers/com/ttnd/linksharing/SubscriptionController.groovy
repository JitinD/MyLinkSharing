package com.ttnd.linksharing

import enums.Seriousness

class SubscriptionController {

    def index() { }

    def save(Long topicId)
    {
        Topic topic = Topic.get(topicId)

        Subscription subscription = new Subscription(user: session.user, topic: topic)

        if(subscription.saveInstance())
        {
            render "${subscription} saved successfully"
        }
        else
        {
            render "${subscription} could not be saved"
        }
    }

    def update(Long id, String serious)
    {
        Subscription subscription = Subscription.get(id)

        Seriousness seriousness = Seriousness.getSeriousness(serious)

        if(subscription && seriousness)
        {
            subscription.seriousness = seriousness

            if(subscription.saveInstance())
                render "Subscription ${subscription} saved successfully"
            else
                render "Subscription ${subscription} could not be saved"
        }
        else
        {
            render "Subscription with id ${id} could not be found."
        }
    }

    def delete(Long id)
    {
        Subscription subscription = Subscription.load(id)

        if(subscription)
        {
            subscription.delete(flush: true)
            render "Subscription ${subscription} deleted."
        }
        else
        {
            flash.error = "${subscription} could not be deleted, ${subscription.errors.allErrors}"
            render "${subscription} could not be deleted, ${subscription.errors.allErrors}"
        }
    }
}
