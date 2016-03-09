package com.ttnd.linksharing

import CO.ResourceSearchCO
import VO.PostVO
import VO.RatingInfoVo
import VO.TopicVo
import enums.Visibility

class ResourceController {

    def index() {}


    private addToReadingItems(Resource resource) {

        Topic topic = resource.topic

        List<User> subscribedUsers = topic.getSubscribedUsersList()

        subscribedUsers.each {
            user ->
                if (resource.createdBy != user)
                    user.addToReadingItems([user: user, resource: resource, isRead: false])
        }
    }


    def delete(Long id) {
        Resource resource = Resource.load(id)
        User user = session.user

        if (user.canDeleteResource(id)) {

            try {
                resource.deleteFile()
                flash.message = "Resource deleted successfully"
            } catch (Exception e) {
                flash.error = "Resource not available"
            }
        }
        else
            flash.error = "User doesn't have valid permissions to delete the resource"


        redirect(controller: "user", action: "index")

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

            User user = session.user
            PostVO postVO = Resource.getPostInfo(id)
            postVO.score = user?.getScore(id)

            if (resource.canViewBy(user))
                render(view: '/resource/show', model: [post: postVO])
            else {
                flash.error = "User doesn't have valid permissions to access the resource"
                redirect(controller: "user", action: "index")
            }
        } else {
            flash.error = "Resource could not be found"
            redirect(controller: "user", action: "index")
        }
    }

    def showTrendingTopics() {
        List result = Topic.getTrendingTopics()
        return result
        //render "${result}"
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
