package com.ttnd.linksharing

class ReadingItemController {

    def index() {}

    def changeIsRead(Long resourceId, Boolean isRead) {

        User user = session.user

        if (ReadingItem.executeUpdate("update ReadingItem set isRead =:isRead where user.id =:userId and resource.id =:resourceId", [userId: user.id, resourceId: resourceId, isRead: isRead])) {
            flash.message = "Reading Item isRead successfully changed. ~SUCCESS~"
        } else {
            flash.error = "Reading Item isRead could not be changed. ~FAILURE~"
        }

        redirect(controller: 'user', action: 'index')
    }
}
