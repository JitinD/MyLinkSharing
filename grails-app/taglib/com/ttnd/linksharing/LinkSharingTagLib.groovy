package com.ttnd.linksharing

import VO.TopicVo

class LinkSharingTagLib {
    //static defaultEncodeAs = [taglib: 'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    static namespace = "ls"


    def markAsRead = {

        attributes ->
            if (session.user) {
                Long resourceId = attributes.id
                Boolean isRead = !attributes.isRead
                User user = session.user

                if (!(user.equals(Resource.get(resourceId).createdBy))) {
                    if (attributes.isRead)
                        out << "<a href = '' class = 'markReadStatus text-success' resourceId = ${resourceId} isRead = ${isRead}>Mark as unread</a>"
                    else
                        out << "<a href = '' class = 'markReadStatus text-danger' resourceId = ${resourceId} isRead = ${isRead}>Mark as read</a>"
                }


            }
    }


    def showResource = {

        attributes ->

            Long resourceId = attributes.id
            if (Resource.isLinkResource(resourceId))
                out << "<a href = ${attributes.url} class = 'pull-right panelIcons' target = '_blank'><span class = 'glyphicon glyphicon-new-window'></span></a>"
            else
                out << "<a href = ${createLink([controller: 'documentResource', action: 'download', params: [id: resourceId]])} class = 'pull-right panelIcons'><span class = 'glyphicon glyphicon-save'></span></a>"

    }


    def canDeleteResoure = {

        attributes, body ->

            User user = session.user

            if (user) {
                /*String href = "${createLink(controller: 'resource', action: 'delete', params: [id: attributes.id])}"*/

                if (user.canDeleteResource(attributes.id))
                    out << body()

            }
    }

    def showSubscribedTopics = {

        attributes ->

            User user = session.user

            List<TopicVo> subscribedTopicsList = user.getSubscribedTopicsList()

            out << "${g.select(name: 'topic', id: 'topic', from: subscribedTopicsList, optionKey: 'id', class: 'btn btn-default btn-sm dropdown-toggle')}"
    }

    def canDeleteTopic = {
        attributes ->

            User user = session.user
            Long topicId = attributes.id

            Topic topic = Topic.get(topicId)

            if (user && topic) {
                if (user.isAdmin || topic.createdBy == user) {

                    String href = "${createLink(controller: 'topic', action: 'delete', params: [id: attributes.id])}"

                    out << "<a href = ${href}><ins>Delete</ins></a>"
                }
            } else
                flash.message = "Either topic or user not available."
    }


    def canUpdateTopic = {
        attributes, body ->

            User user = session.user
            Long topicId = attributes.id

            Topic topic = Topic.get(topicId)

            if (user && topic) {
                if (user.isAdmin || user.equals(topic.createdBy)) {
                    out << body()
                }
            } else
                flash.error = "Either topic or user not available."
    }

    def canInviteToTopic = {
        attributes, body ->

            User user = session.user
            Long topicId = attributes.id

            Topic topic = Topic.get(topicId)

            if (user && topic) {
                if (user.isAdmin || Subscription.findByUserAndTopic(user, topic)) {
                    out << body()
                }
            } else
                flash.error = "Either topic or user not available."
    }

    def showSeriousness = {
        attributes ->

            Long topicId = attributes.id
            User user = session.user

            if (user) {
                Subscription subscription = user.getSubscription(topicId)

                if (subscription)
                    out << g.select(class: 'seriousness btn-default', optionKey: 'key', topicId: topicId, name: 'seriousness', from: enums.Seriousness.values(), value: subscription.seriousness)

            } else
                flash.error = "Either topic or user not available."

    }

    def showVisibility = {

        attributes ->

            Long topicId = attributes.id
            User user = session.user

            if (user) {
                Topic topic = Topic.get(topicId)

                if (topic) {

                    String topicName = topic.name
                    out << g.select(class: 'visibility btn-default', optionKey: 'key', topicName: topicName, name: 'visibility', from: enums.Visibility.values(), value: topic.visibility)
                } else
                    flash.error = "User not subscribed to topic"
            } else
                flash.error = "Either topic or user not available."
    }

    def userImage = {

        attributes ->

            Long userId = attributes.id

            if (userId) {

                String src = "/user/image/${userId}"

                out << "<img src = ${src} class='img img-thumbnail img-responsive image' />"

            }
    }

    def showSubscribe = {
        attributes ->

            User user = session.user
            Long topicId = attributes.id
            Topic topic = Topic.get(topicId)

            String hrefSubscribe = "${createLink(controller: 'Subscription', action: 'save', params: [topicId: topicId])}"

            String hrefUnsubscribe = "${createLink(controller: 'Subscription', action: 'delete', params: [topicId: topicId])}"

            if (user) {

                if (topic && !(user.equals(topic.createdBy))) {
                    if (user.isSubscribed(topicId))
                        out << "<a class='col-xs-4' href = ${hrefUnsubscribe}><ins>Unsubscribe</ins></a>"
                    else
                        out << "<a class='col-xs-4' href = ${hrefSubscribe}><ins>Subscribe</ins></a>"
                } else
                    out << "<div class = 'col-xs-4'></div>"
            } else
                out << "<div class = 'col-xs-4'></div>"
    }

    def subscriptionCount = {
        attributes ->

            if (attributes.userId) {
                User user = User.get(attributes.userId)
                Integer numTopicsSubscribed = Subscription.countByUser(user)

                out << numTopicsSubscribed
            }

            if (attributes.topicId) {
                Topic topic = Topic.get(attributes.topicId)
                Integer numUsersSubscribed = Subscription.countByTopic(topic)

                out << numUsersSubscribed
            }
    }


    def topicCount = {
        attributes ->

            if (attributes.userId) {
                User user = User.get(attributes.userId)
                Integer numTopicsCreated = Topic.countByCreatedBy(user)

                out << numTopicsCreated
            }
    }

    def resourceCount = {
        attributes ->

            if (attributes.topicId) {
                Topic topic = Topic.get(attributes.topicId)
                Integer numPosts = Resource.countByTopic(topic)

                out << numPosts
            }
    }


    def showEdit = {

        attributes, body ->

            if (attributes.id) {
                User user = session.user

                if (user) {
                    Resource resource = Resource.get(attributes.id)

                    if (resource) {
                        if (user.isAdmin || user.equals(resource.createdBy))
                            out << body()
                    }
                }
            }
    }

    def canRate = {
        attributes, body ->

            User user = session.user
            Resource resource = Resource.get(attributes.id)

            if (attributes.score && !(user.equals(resource.createdBy)) && Subscription.findByUserAndTopic(user, resource.topic))
                out << body()
    }
}
