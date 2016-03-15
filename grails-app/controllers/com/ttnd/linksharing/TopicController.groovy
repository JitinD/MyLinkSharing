package com.ttnd.linksharing

import CO.SearchCO
import DTO.EmailDTO
import enums.Visibility
import grails.converters.JSON

class TopicController {

    def emailService
    def messageSource

    def show(Long id, SearchCO searchCO) {

        Topic topic = Topic.get(id)

        searchCO.max = searchCO.max ?: 5
        searchCO.offset = searchCO.offset ?: 0


        if (topic) {

            List<Resource> topicPosts = topic.getTopicPosts(searchCO)

            if (topic.visibility == Visibility.PUBLIC) {
                render(view: "show", model: [topic: topic, subscribedUsers: topic.subscribedUsers, topicPosts: topicPosts, searchCO: searchCO, topicPostsCount: topic.getTopicPostsCount()])
            } else if (topic.visibility == Visibility.PRIVATE) {
                User user = session.user

                if (Subscription.findByUserAndTopic(user, topic)) {
                    render(view: "show", model: [topic: topic, subscribedUsers: topic.subscribedUsers, topicPosts: topicPosts, searchCO: searchCO, topicPostsCount: topic.getTopicPostsCount()])
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

    def save(String topicName, String newTopicName, String visibility) {
        User user = session.user
        Map jsonResponseMap = [:]

        Topic existingTopic = Topic.findByCreatedByAndName(user, topicName)

        if (existingTopic) {

            if (newTopicName || visibility) {

                if (newTopicName)
                    existingTopic.name = newTopicName

                if (visibility)
                    existingTopic.visibility = Visibility.getVisibility(visibility)
            }else{
                flash.error = g.message(code: "is.present.topic")
                redirect(uri: [request.forwardURI - request.contextPath])
            }

            if (existingTopic.saveInstance()) {
                jsonResponseMap.message = g.message(code: "is.updated.topic")

            } else {
                jsonResponseMap.error = g.message(code: "not.updated.topic")

            }

            JSON jsonResponse = jsonResponseMap as JSON
            render jsonResponse

        } else {
            Topic newTopic = new Topic(name: topicName, visibility: Visibility.getVisibility(visibility), createdBy: user)

            if (newTopic.saveInstance())
                flash.message = g.message(code: "is.saved.topic")
            else
                flash.error = g.message(code: "not.saved.topic")

            redirect(uri: [request.forwardURI - request.contextPath])
        }

        //if(newTopicName){
        /*flash.message = g.message(code: "is.saved.topic")
        jsonResponseMap.message = g.message(code: "is.saved.topic")
        redirect(uri: [request.forwardURI - request.contextPath])*/
        //}
        //else
        //{
        /*log.info "1. ${request.forwardURI - request.contextPath}"
        log.info "2. ${request.getRequestURI()}"
        log.info "3. ${request.servletPath}"
        log.info "4. ${request.contextPath}"
        log.info "5 ${request.getRequestURL()}"

        flash.message = g.message(code: "is.saved.topic")
        redirect(uri: [request.forwardURI - request.contextPath])

    //}
}
}*/

    }


    def delete(Long id) {

        Topic topic = Topic.get(id)
        User user = session.user

        if (topic) {
            if (user.isAdmin || (user.equals(topic.createdBy))) {
                topic.delete(flush: true)
                flash.message = g.message(code: "is.deleted.topic")
            } else
                flash.error = g.message(code: "not.deleted.topic")
        } else
            flash.error = g.message(code: "not.found.topic")

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
