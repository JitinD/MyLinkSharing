package com.ttnd.linksharing

import enums.Visibility

class TopicController {

    def index() { }

    def show(Long id)
    {
        Topic topic = Topic.get(id)

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
}
