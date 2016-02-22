package com.ttnd.linksharing

class ReadingItem {
    Boolean isRead;
    Date dateCreated;
    Date lastUpdated;

    static constraints = {
        resource(nullable: false, unique: ['user'])
        user(nullable: false)
        isRead(nullable: false)
    }

    static belongsTo = [resource: Resource, user: User]

    public ReadingItem saveInstance() {

        ReadingItem readingItem = this

        readingItem.validate()

        if (readingItem.hasErrors())
        {
            log.error("ReadingItem has validation errors : ${readingItem.errors}")

            return null
        }
        else
        {
            readingItem.save(failOnError: true, flush: true)
            log.info "${readingItem} saved successfully"

            return readingItem
        }
    }
}