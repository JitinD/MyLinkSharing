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

    public ResourceRating saveInstance() {

        ResourceRating resourceRating = this

        resourceRating.validate()

        if (resourceRating.hasErrors())
        {
            log.error("Resource rating has validation errors : ${resourceRating.errors}")

            return null
        }
        else
        {
            resourceRating.save(failOnError: true, flush: true)
            log.info "${resourceRating} saved successfully"

            return resourceRating
        }
    }
}