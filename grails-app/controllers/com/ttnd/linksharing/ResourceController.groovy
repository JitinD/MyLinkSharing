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
        } else
            render "q variable missing"

    }


    def show(Long id) {
        Resource resource = Resource.get(id)

        if (resource) {
            RatingInfoVo ratingInfoVo = resource.getRatingInfo()
            return ${ratingInfoVo}
            //render "${ratingInfoVo}"
        } else {
            render "Resource could not be found"

        }
    }

    def showTrendingTopics() {
        List result = Topic.getTrendingTopics()
        return result
        //render "${result}"
    }


    def saveLinkResource(LinkResource linkResource) {
        linkResource.createdBy = session.user

        if (linkResource.saveInstance()) {

            flash.message = "Link resource successfully added. ~SUCCESS~"

            //render flash.message
        } else {
            flash.error = linkResource.errors.allErrors.collect{message(error:it)}  //"Link resource could not be added. ~FAILURE~"

            //redirect(controller: 'user', action: 'index')
        }
        redirect uri: "/"
    }


    def saveDocResource(String filePath, String description, String topicName) {
        User user = session.user
        Topic topic = Topic.findByNameAndCreatedBy(topicName, user)

        Resource docResource = new DocumentResource(description: description, topic: topic, createdBy: user, filePath: filePath)

        if (docResource.saveInstance()) {

            flash.message = "Doc resource successfully added. ~SUCCESS~"
            render flash.message
        } else {
            flash.error = "Doc resource could not be added. ~FAILURE~"
            redirect(controller: 'user', action: 'index')
        }
    }
}
