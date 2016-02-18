package com.ttnd.linksharing

import enums.Seriousness
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

    String toString()
    {
        return name
    }

    def afterInsert =
            {
                Topic.withNewSession
                        {
                            Subscription subscription = new Subscription(user: this.createdBy, topic: this, seriousness: Seriousness.VERY_SERIOUS)

                            if(subscription.save())
                                log.info "Subscriptions saved successfully"
                            else
                                log.info "Error while saving subscriptions"
                        }
            }
}