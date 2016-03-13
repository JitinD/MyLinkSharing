package com.ttnd.linksharing

class ReadingItemController {

    def changeIsRead(Long resourceId, Boolean isRead) {

        User user = session.user
        Map jsonResponseMap = [:]

        if (ReadingItem.executeUpdate("update ReadingItem set isRead =:isRead where user.id =:userId and resource.id =:resourceId", [userId: user.id, resourceId: resourceId, isRead: isRead])) {
            flash.message = "Reading status changed successfully"
        } else {
            flash.error = "Reading status could not be changed"
            jsonResponseMap.error = flash.error
        }
    }
}
