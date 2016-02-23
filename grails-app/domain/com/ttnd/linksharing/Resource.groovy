package com.ttnd.linksharing

import CO.ResourceSearchCO

abstract class Resource {

    String description;
    User createdBy;
    Date dateCreated;
    Date lastUpdated;

    static constraints = {
        description(nullable: false, blank: false)
        createdBy(nullable: false, blank: false)
        topic(nullable: false, blank: false)
    }

    static mapping = {
        description type: 'text'
    }

    static belongsTo = [topic: Topic]
    static hasMany = [ratings: ResourceRating, readItems: ReadingItem]


    static namedQueries = {
        search {
            ResourceSearchCO resourceSearchCO ->
                if (resourceSearchCO.topicId) {
                    eq('topic.id', resourceSearchCO.topicId)
                }

                if (resourceSearchCO.visibility) {
                    eq('topic.visibility', resourceSearchCO.visibility)
                }
        }
    }

    public Resource saveInstance() {

        Resource resource = this

        resource.validate()

        if (resource.hasErrors()) {
            log.error("Resource has validation errors : ${resource.errors}")

            return null
        } else {
            resource.save(failOnError: true, flush: true)
            log.info "${resource} saved successfully"

            return resource
        }
    }
}