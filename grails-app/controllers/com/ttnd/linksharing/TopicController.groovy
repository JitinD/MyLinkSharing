package com.ttnd.linksharing

import DTO.EmailDTO
import enums.Visibility
import grails.converters.JSON

class TopicController {

    def emailService
    def messageSource

    def show(Long id) {
        Topic topic = Topic.get(id)

        if (topic) {

            List<Resource> topicPosts = topic.getTopicPosts()

            if (topic.visibility == Visibility.PUBLIC) {
                render(view: "show", model: [topic: topic, subscribedUsers: topic.subscribedUsers, topicPosts: topicPosts])
            } else if (topic.visibility == Visibility.PRIVATE) {
                User user = session.user

                if (Subscription.findByUserAndTopic(user, topic)) {
                    render(view: "show", model: [subscribedUsers: topic.subscribedUsers, topicPosts: topicPosts])
                } else {
                    flash.error = g.message(code: "not.found.subscription")
                    redirect(controller: "login", action: "index")
                }
            }
        } else {
            flash.error = g.message(code: "not.found.topic")
            redirect(controller: "login", action: "index")
        }
    }

    def save(String topicName, String visibility) {
        User user = session.user
        Topic topic = Topic.findOrCreateByCreatedByAndName(user, topicName)
        Map jsonResponseMap = [:]

        topic.visibility = visibility

        if (topic.saveInstance()) {
            jsonResponseMap.message = g.message(code: "is.saved.topic")

        } else {
            jsonResponseMap.error = g.message(code: "not.saved.topic")
        }

        JSON jsonResponse = jsonResponseMap as JSON
        render jsonResponse
    }


    def delete(Long id) {

        Topic topic = Topic.get(id)
        User user = session.user

        if (topic) {
            if (user.isAdmin || (topic.createdBy == user)) {
                topic.delete(flush: true)
                flash.message = g.message(code: "is.deleted.topic")
            }
            else
                flash.error = g.message(code: "not.accessible.topic")
        } else
            flash.error = g.message(code: "not.accessible.topic")

        redirect(controller: 'login', action: 'index')

    }

    def invite(Long topic, String emailID) {

        Topic topicInstance = Topic.get(topic)

        String to = emailID
        String subject = g.message(code: "send.topic.invitation")
        String hostURL = grailsApplication.config.grails.serverURL

        EmailDTO emailDTO = new EmailDTO(to: to, subject: subject, model: [id: topic, hostURL: hostURL])

        if (topicInstance == null)
            flash.error = g.message(code: "not.found.topic")
        else {
            emailService.sendMail(emailDTO)
            flash.message = g.message(code: "is.sent.email")
        }

        redirect(controller: "login", action: "index")
    }

    def join(Long id) {

        if (session.user) {

            User user = session.user
            Topic topic = Topic.get(id)
            Subscription subscription = new Subscription(user: user, topic: topic)

            if (subscription.saveInstance())
                flash.message = g.message(code: "is.saved.subscription")
            else
                flash.error = g.message(code: "not.saved.subscription")

            redirect(controller: "login", action: "index")
        }
    }
}
