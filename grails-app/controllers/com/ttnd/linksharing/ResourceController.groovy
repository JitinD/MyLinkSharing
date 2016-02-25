package com.ttnd.linksharing

import CO.ResourceSearchCO
import VO.RatingInfoVo
import VO.TopicVo
import enums.Visibility

class ResourceController {

    def index() {}

    def delete(Long id) {
        Resource resource = Resource.load(id)

        try {
            resource.delete(flush: true)
            render "${resource} deleted successfully."
        } catch (Exception e) {
            flash.message = "Resource could not be deleted"
            render e.message
        }

    }

    def search(ResourceSearchCO resourceSearchCO) {
        if (resourceSearchCO.q) {
            resourceSearchCO.visibility = Visibility.PUBLIC

            List<Resource> resources = Resource.search(resourceSearchCO).list()
            render "${resources}"
        }
        else
            render "q variable missing"

    }


    def show(Long id) {
        Resource resource = Resource.get(id)

        if (resource) {
            RatingInfoVo ratingInfoVo = resource.getRatingInfo()
            render "${ratingInfoVo}"
        } else {
            render "Resource could not be found"

        }
    }

    def showTrendingTopics() {
        List result = Topic.getTrendingTopics()

        render "${result}"
    }

}
