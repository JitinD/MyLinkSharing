package com.ttnd.linksharing

import CO.ResourceSearchCO
import VO.PostVO
import VO.TopicVo
import enums.Visibility

class ResourceController {


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
        } else
            flash.error = "User doesn't have valid permissions to delete the resource"


        redirect(controller: "login", action: "index")

    }

    def search(ResourceSearchCO resourceSearchCO) {
        List<PostVO> posts = []

        if (resourceSearchCO.q)
            resourceSearchCO.visibility = Visibility.PUBLIC

        List<Resource> resources = Resource.search(resourceSearchCO).list(max: resourceSearchCO.max, offset: resourceSearchCO.offset)

        posts = resources?.collect { Resource.getPostInfo(it.id) }

        if (resourceSearchCO.global) {
            List<PostVO> topPosts = Resource.getTopPosts()
            List<TopicVo> trendingTopics = Topic.getTrendingTopics()

            render(view: "/resource/search", model: [topPosts: topPosts, trendingTopics: trendingTopics, posts: posts])
        } else
            render(template: '/resource/searchedPosts', model: [topicPosts: posts])

    }


    def show(Long id) {
        Resource resource = Resource.get(id)

        if (resource) {

            User user = session.user
            PostVO postVO = Resource.getPostInfo(id)
            postVO.score = user?.getScore(id)

            if (resource.canViewBy(user)){
                List<TopicVo> trendingTopics = Topic.getTrendingTopics()

                render(view: '/resource/show', model: [post: postVO, trendingTopics: trendingTopics])
            }
            else {
                flash.error = g.message(code: "not.accessible.resource")
                redirect(controller: "user", action: "index")
            }
        } else {
            flash.error = g.message(code: "not.found.resource")

            redirect(controller: "user", action: "index")
        }
    }

    def showTrendingTopics() {
        List result = Topic.getTrendingTopics()
        return result
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

    def save(Long id, String description) {

        Resource resource = Resource.get(id)

        if (resource) {
            resource.description = description

            if(resource.saveInstance()){

                flash.message = g.message(code: "is.updated.resource")
            }
            else
                flash.error = g.message(code: "not.updated.resource")
        } else {
            flash.error = g.message(code: "not.found.resource")
        }

        redirect(uri: "/resource/show/${id}")
    }
}
