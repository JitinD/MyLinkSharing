package com.ttnd.linksharing

import enums.Visibility

class Topic {

    String name;
    User createdBy;
    Visibility visibility;
    Date dateCreated;
    Date lastUpdated;

    static constraints = {
        name(nullable: false, blank: false, unique: ['createdBy'])
        visibility(inList: Visibility.values().toList(), nullable: false)
        createdBy(nullable: false)
    }

    static hasMany = [subscriptions: Subscription, resources: Resource]
    static belongsTo = [user: User]
}