package com.ttnd.linksharing

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_NORMAL'])
class ReadingItemController {

    def changeIsRead(Long resourceId, Boolean isRead) {

        User user = session.user
        Map jsonResponseMap = [:]

        if (ReadingItem.executeUpdate("update ReadingItem set isRead =:isRead where user.id =:userId and resource.id =:resourceId", [userId: user.id, resourceId: resourceId, isRead: isRead])) {
            jsonResponseMap.message = g.message(code: "is.changed.readingStatus")
        } else {
            flash.error = g.message(code: "not.changed.readingStatus")
            jsonResponseMap.error = flash.error
        }

        JSON jsonResponse = jsonResponseMap as JSON
        render jsonResponse
    }
}
