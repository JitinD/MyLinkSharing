package com.ttnd.linksharing

class LinkSharingTagLib {
    //static defaultEncodeAs = [taglib: 'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    static namespace = "ls"


    def markAsRead = {

        attributes ->
            if (session.user) {
                String href = "${createLink(controller: 'readingItem', action: 'changeIsRead', params: [resourceId: attributes.id, isRead: !attributes.isRead])}"

                if (!attributes.isRead)
                    out << "<a href = ${href}  class = 'text-danger'>Mark as read</a>"
            }
    }


    def showResource = {

        attributes ->

            Long resourceId = attributes.id
            if (Resource.isLinkResource(resourceId))
                out << "<a href = ${attributes.url} class = 'pull-right' target = '_blank'><ins>View Site</ins></a>&nbsp;"
            else
                out << "<a href = ${createLink([controller: 'documentResource', action: 'download', params: [id: resourceId]])} class = 'pull-right'><ins>Download</ins></a> &nbsp;"

    }


    def canDeleteResoure = {

        attributes ->

            User user = session.user

            if (user) {
                String href = "${createLink(controller: 'resource', action: 'delete', params: [id: attributes.id])}"

                if (user.canDeleteResource(attributes.id))
                    out << "<a href = ${href}><ins>Delete</ins></a>"

            }
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

            String hrefSubscribe = "${createLink(controller: 'Subscription', action: 'save', params: [topicId: topicId])}"

            String hrefUnsubscribe = "${createLink(controller: 'Subscription', action: 'delete', params: [topicId: topicId])}"

            if (user && topicId) {
                if (user.isSubscribed(topicId))
                    out << "<a class='col-xs-4' href = ${hrefUnsubscribe}><ins>Unsubscribe</ins></a>"
                else
                    out << "<a class='col-xs-4' href = ${hrefSubscribe}><ins>Subscribe</ins></a>"
            }
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
        if (session.user)
            out << "<a href=''><ins>Edit</ins></a>"
    }

    def canRate = {
        attributes, body ->

            if (attributes.score)
                out << body()
    }
}
