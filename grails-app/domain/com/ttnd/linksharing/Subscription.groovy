package com.ttnd.linksharing

import enums.Seriousness

class Subscription {

    Seriousness seriousness;
    Date dateCreated;
    Date lastUpdated;

    static constraints = {
        user(nullable: false)
        topic(nullable: false, unique: ['user'])
        seriousness(inList: Seriousness.values().toList(), nullable: false, blank: false)
    }

    static belongsTo = [topic: Topic, user: User]

}