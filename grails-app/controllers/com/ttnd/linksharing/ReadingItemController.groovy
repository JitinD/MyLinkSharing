package com.ttnd.linksharing

class ReadingItemController {

    def changeIsRead(Long resourceId, Boolean isRead) {

        User user = session.user
        Map jsonResponseMap = [:]

        if (ReadingItem.executeUpdate("update ReadingItem set isRead =:isRead where user.id =:userId and resource.id =:resourceId", [userId: user.id, resourceId: resourceId, isRead: isRead])) {
            flash.message = g.message(code: "is.changed.readingStatus")
        } else {
            flash.error = g.message(code: "not.changed.readingStatus")
            jsonResponseMap.error = flash.error
        }
    }
}
