package com.ttnd.linksharing

import grails.converters.JSON

class ReadingItemController {

    def index() {}

    def changeIsRead(Long resourceId, Boolean isRead) {

        User user = session.user
        Map jsonResponseMap = [:]

        if (ReadingItem.executeUpdate("update ReadingItem set isRead =:isRead where user.id =:userId and resource.id =:resourceId", [userId: user.id, resourceId: resourceId, isRead: isRead])) {
            flash.message = "Reading Item isRead successfully changed. ~SUCCESS~"
            jsonResponseMap.message = "Success"
        } else {
            flash.error = "Reading Item isRead could not be changed. ~FAILURE~"
            jsonResponseMap.error = "Failure"
        }

        JSON jsonResponseObject = jsonResponseMap as JSON

        redirect(controller: 'user', action: 'index')
    }
}
