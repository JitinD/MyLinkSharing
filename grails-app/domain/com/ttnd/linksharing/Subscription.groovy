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

    public Subscription saveInstance() {

        Subscription subscription = this

        subscription.validate()

        if (subscription.hasErrors())
        {
            log.error("Subscription has validation errors : ${subscription.errors}")

            return null
        }
        else
        {
            subscription.save(failOnError: true, flush: true)
            log.info "${subscription} saved successfully"

            return subscription
        }
    }

}