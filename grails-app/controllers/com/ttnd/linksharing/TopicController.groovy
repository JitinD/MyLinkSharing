package com.ttnd.linksharing

import CO.ResourceSearchCO
import enums.Visibility

class TopicController {

    def index() { }

    def show(ResourceSearchCO resourceSearchCO)
    {
        Topic topic = Topic.get(resourceSearchCO.topicId)

        if(topic)
        {
            if(topic.visibility == Visibility.PUBLIC)
            {
                render "Topic found and its public. ~SUCCESS~"
            }
            else if(topic.visibility == Visibility.PRIVATE)
            {
                User user = session.user

                if(Subscription.findByUserAndTopic(user, topic))
                {
                    render "User is subscribed to the topic. ~SUCCESS~"
                }
                else
                {
                    flash.error = "User is not subscribed to the topic."
                    redirect(controller: "login", action: "index")
                }
            }
        }
        else
        {
            flash.error = "Not a valid topic id."
            redirect(controller: "login", action: "index")
        }
    }

    def save(String topicName, String visibility)
    {
        User user = session.user
        Topic topic = new Topic(createdBy: user, name: topicName, visibility: Visibility.getVisibility(visibility))

        if(topic.saveInstance())
        {
            flash.message = "Topic saved successfully"
            render "Topic saved. ~SUCCESS~"
        }
        else
        {
            flash.error = "Topic could not be saved"
            render "Topic could not be saved. ~FAILURE~"
            render "${topic.errors.allErrors.collect { message(error: it) }}"

        }
    }
}
