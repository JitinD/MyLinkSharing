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
}