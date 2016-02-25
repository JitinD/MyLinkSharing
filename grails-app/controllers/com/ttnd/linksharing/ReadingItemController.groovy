package com.ttnd.linksharing

class ReadingItemController {

    def index() {}

    def changeIsRead(Long id, Boolean isRead) {
        ReadingItem readingItem = ReadingItem.get(id)

        if (ReadingItem.executeUpdate("update ReadingItem as r set r.isRead=:isRead where r.id=:id", [id: id, isRead: isRead])) {
            readingItem.refresh()
            render "Reading Item isRead successfully changed. ~SUCCESS~"
        } else {
            render "Reading Item isRead could not be changed. ~FAILURE~"
        }
    }
}
