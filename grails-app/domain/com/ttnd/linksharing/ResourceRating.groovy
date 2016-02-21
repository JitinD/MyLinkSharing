package com.ttnd.linksharing

class ResourceRating {

    Integer score;
    Date dateCreated;
    Date lastUpdated;

    static constraints = {
        resource(nullable: false, unique: ['user'])
        user(nullable: false)
        score(nullable: false, min: 1, max: 5)
    }

    static belongsTo = [resource: Resource, user: User]

    public static ResourceRating save(ResourceRating resourceRating) {

        resourceRating.validate()

        if (resourceRating.hasErrors())
        {
            resourceRating.errors.each {
                log.error("error saving resourceRating", it)
            }

            return null
        }
        else
        {
            resourceRating.save(failOnError: true, flush: true)

            return resourceRating
        }
    }
}