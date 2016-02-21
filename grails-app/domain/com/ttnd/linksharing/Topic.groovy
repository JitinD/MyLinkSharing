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

    public static Topic save(Topic topic) {

        topic.validate()

        if (topic.hasErrors())
        {
            topic.errors.each {
                log.error("error saving topic", it)
            }

            return null
        }
        else
        {
            topic.save(failOnError: true, flush: true)

            return topic
        }
    }


    def afterInsert =
            {
                Topic.withNewSession
                        {
                            Subscription subscription = new Subscription(user: this.createdBy, topic: this, seriousness: Seriousness.VERY_SERIOUS)

                            if(Subscription.save(subscription)) {
                                this.createdBy.addToSubscriptions(subscription)
                                log.info "${subscription} saved successfully"
                            }
                            else
                                log.error "Error saving ${subscription.errors.allErrors}"
                        }
            }
}