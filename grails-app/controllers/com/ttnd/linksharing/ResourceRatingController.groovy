package com.ttnd.linksharing

class ResourceRatingController {

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
                flash.message = g.message(code: "is.rated.resource")
            else
                flash.error = g.message(code: "not.rated.resource")
        }
        else
            flash.error = g.message(code: "not.found.resource")

        redirect(url: request.getHeader('referer'))
    }
}