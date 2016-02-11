package com.ttnd.linksharing

class ReadingItem {
    Resource resource;
    User user;
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