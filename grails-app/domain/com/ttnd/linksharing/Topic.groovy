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

    static mapping = {
                sort name:'asc'
            }

    static hasMany = [subscriptions: Subscription, resources: Resource]

    String toString() {
        return name ?: ""
    }

    public Topic saveInstance() {

        Topic topic = this

        topic.validate()

        if (topic.hasErrors()) {
            log.error("Topic has validation errors : ${topic.errors}")

            return null
        } else {
            topic.save(failOnError: true, flush: true)
            log.info "${topic} saved successfully"

            return topic
        }
    }


    def afterInsert() {
        Subscription.withNewSession
                {
                    Subscription subscription = new Subscription(user: this.createdBy, topic: this, seriousness: Seriousness.VERY_SERIOUS)

                    if (subscription.saveInstance()) {
                        this.createdBy.addToSubscriptions(subscription)
                        log.info "${subscription} saved successfully"
                    } else
                        log.error "Error saving ${subscription.errors.allErrors}"
                }
    }
}