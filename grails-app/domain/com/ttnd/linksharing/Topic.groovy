package com.ttnd.linksharing

import VO.TopicVo
import enums.Seriousness
import enums.Visibility
import Logging.LogSql

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
        sort name: 'asc'
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


    public static List<TopicVo> getTrendingTopics() {

        List<TopicVo> topicVoList = []

        List result = Resource.createCriteria().list(max: 5) {

                        projections {
                            createAlias('topic', 't')
                            property('t.id', 'topicId')
                            property('t.name', 'topicName')
                            property('t.visibility', 'topicVisibility')
                            count('id', 'resource_count')
                            property('t.createdBy')
                        }

                        groupProperty('t.id')
                        eq('t.visibility', Visibility.PUBLIC)
                        order('t.name', 'desc')
                        order('resource_count', 'desc')

                    }



       result.each {
            record -> topicVoList.add(new TopicVo(id: record[0], name: record[1], visibility: record[2], count: record[3], createdBy: record[4]))
        }

        return topicVoList
    }

    def afterInsert() {
        Subscription.withNewSession
                {
                    Subscription subscription = new Subscription(user: this.createdBy, topic: this)

                    if (subscription.saveInstance()) {
                        //this.createdBy.addToSubscriptions(subscription)
                        log.info "${subscription} saved successfully"
                    } else
                        log.error "Error saving ${subscription.errors.allErrors}"
                }
    }

}