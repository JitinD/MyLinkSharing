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

    public static Subscription save(Subscription subscription) {

        subscription.validate()

        if (subscription.hasErrors())
        {
            subscription.errors.each {
                log.error("error saving subscription", it)
            }

            return null
        }
        else
        {
            subscription.save(failOnError: true, flush: true)

            return subscription
        }
    }

}