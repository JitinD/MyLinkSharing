package com.ttnd.linksharing

import CO.ResourceSearchCO
import DTO.EmailDTO
import enums.Visibility
import grails.converters.JSON

class TopicController {

    def emailService
    def messageSource

    def index() { }

    def show(Long id)
    {
        Topic topic = Topic.get(id)

        if(topic)
        {

            List<Resource> topicPosts = topic.getTopicPosts()

            if(topic.visibility == Visibility.PUBLIC)
            {
                //render "Topic found and its public. ~SUCCESS~"

                render (view: "show", model: [topic: topic, subscribedUsers : topic.subscribedUsers, topicPosts : topicPosts])
            }
            else if(topic.visibility == Visibility.PRIVATE)
            {
                User user = session.user

                if(Subscription.findByUserAndTopic(user, topic))
                {
                    //render "User is subscribed to the topic. ~SUCCESS~"
                    render (view: "show", model: [subscribedUsers : topic.subscribedUsers, topicPosts : topicPosts])
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
        Topic topic = Topic.findOrCreateByCreatedByAndName(user, topicName)
        Map jsonResponseMap = [:]

        topic.visibility = visibility

        if(topic.saveInstance())
        {
            flash.message = "Topic saved successfully"
            jsonResponseMap.message = "Topic saved successfully"

            //render "Topic saved. ~SUCCESS~"
        }
        else
        {
            flash.error = "Topic could not be saved"
            jsonResponseMap.error = "Topic could not be saved"
            //render "Topic could not be saved. ~FAILURE~"
            //render "${topic.errors.allErrors.collect { message(error: it) }}"

        }

        JSON jsonResponse = jsonResponseMap as JSON
        render jsonResponse
    }


    def delete(Long id){

        Topic topic  = Topic.get(id)
        User user = session.user

        if(topic)
        {
            if(user.isAdmin || (topic.createdBy == user))
                topic.delete(flush: true)

        }
        else
            flash.error = "Topic unavailable."

        redirect(controller: 'login', action: 'index')

    }

    def invite(Long topic, String emailID){
        
        Topic topicInstance = Topic.get(topic)

        String to = emailID
        String subject = "Invitation for a new topic."
        String hostURL = grailsApplication.config.grails.serverURL

        EmailDTO emailDTO = new EmailDTO(to: to, subject: subject, model: [id: topic, hostURL: hostURL])

        if(topicInstance == null)
            flash.error = "Topic could not be found."
        else
        {
            emailService.sendMail(emailDTO)
            flash.message = "Email sent"
        }

        redirect(controller: "login", action: "index")
    }

    def join(Long id){

        if(session.user){

            User user = session.user
            Topic topic = Topic.get(id)
            Subscription subscription = new Subscription(user: user, topic: topic)

            if(subscription.saveInstance())
                flash.message = "You have subscribed to this topic successfully."
            else
                flash.error = "Failure. Could not subscribe to the topic."

            redirect(controller: "login", action: "index")
        }
    }
}
