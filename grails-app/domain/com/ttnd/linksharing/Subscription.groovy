package com.ttnd.linksharing

import enums.Seriousness

class Subscription {

    Topic topic;
    User user;
    Seriousness seriousness;
    Date dateCreated;
    Date lastUpdated;

    static constraints = {
        user(nullable: false)
        topic(nullable: false, unique: ['user'])
        seriousness(nullable: false)
    }

    static belongsTo = [topic: Topic, user: User]

}