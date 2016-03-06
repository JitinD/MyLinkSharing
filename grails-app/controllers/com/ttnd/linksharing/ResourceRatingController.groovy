package com.ttnd.linksharing

class ResourceRatingController {

    def index() {}

    def save(Long resourceId, Integer score)
    {
        Resource resource = Resource.get(resourceId)
        User user = session.user

        ReadingItem readingItem = ReadingItem.findByUserAndResource(user, resource)

        if(readingItem)
        {
            ResourceRating resourceRating = ResourceRating.findOrCreateByUserAndResource(user, resource)
            resourceRating.score = score

            if(resourceRating.saveInstance())
                flash.message = "Resource rated successfully"
            else
                flash.error = "Resource couldn't be rated! Try again later."
        }
        else
            flash.error = "Resource not available."

        redirect(controller: "user", action: "index")
    }
}