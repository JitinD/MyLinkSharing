package com.ttnd.linksharing

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

    static  belongsTo = [topic: Topic]
    static hasMany = [ratings: ResourceRating, readItems: ReadingItem]


    public static Resource save(Resource resource) {

        resource.validate()

        if (resource.hasErrors())
        {
            resource.errors.each {
                log.error("error saving resource", it)
            }

            return null
        }
        else
        {
            resource.save(failOnError: true, flush: true)

            return resource
        }
    }
}