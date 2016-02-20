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

    public static ReadingItem save(ReadingItem readingItem) {

        readingItem.validate()

        if (readingItem.hasErrors())
        {
            readingItem.errors.each {
                log.error("error saving readingItem", it)
            }

            return null
        }
        else
        {
            readingItem.save(failOnError: true, flush: true)

            return readingItem
        }
    }
}