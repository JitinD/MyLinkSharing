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
                    out << "<a href = ${href}>Mark as read</a>"
            }
    }


    def showResource = {

        attributes ->
            if (Resource.isLinkResource(attributes.id))
                out << "<a href = ${attributes.url} class = 'pull-right' target = '_blank'> View Full Site </a>&nbsp;"
            else
                out << "<a href = ${attributes.filePath} class = 'pull-right'>Download</a> &nbsp;"

    }


    def canDeleteResoure = {

        attributes ->

            User user = session.user

            String href = "${createLink(controller: 'resource', action: 'delete', params: [id: attributes.id])}"

            if(user.canDeleteResource(attributes.id))
                out << "<a href = ${href}>Delete</a>"
    }
}
