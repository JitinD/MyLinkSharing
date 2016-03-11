package com.ttnd.linksharing

import CO.ResourceSearchCO
import CO.SearchCO
import CO.TopicSearchCO
import VO.PostVO
import VO.TopicVo
import enums.Visibility

class UserController {

    def assetResourceLocator
    def topicService
    def subscriptionService

    def index() {

        render(view: 'index', model: [user: session.user?.getInfo(), subscribedTopics: session.user?.subscribedTopics,
                                      trendingTopics: Topic.getTrendingTopics(), inboxPosts: session.user?.getInboxPosts(),
                                      subscribedTopicsList: session.user?.getSubscribedTopicsList()])
    }

    def profile(ResourceSearchCO resourceSearchCO){

        User user = User.get(resourceSearchCO.id)

        if(session.user){
            if(!(session.user.isAdmin || session.user.equals(user))){
                resourceSearchCO.visibility = Visibility.PUBLIC
            }
        }else
            resourceSearchCO.visibility = Visibility.PUBLIC

        List<PostVO> createdPosts = user.getCreatedPosts()

        render (view: 'profile', model: [createdPosts: createdPosts, user: user.getInfo()])
    }

    def image(Long id) {
        User user = User.get(id)
        byte[] photo

        if (user.photo)
            photo = user.photo
        else {

            photo = assetResourceLocator.findAssetForURI('dummy.jpeg').byteArray
        }
        response.outputStream << photo
        response.outputStream.flush()
    }

    def topics(Long id){

        TopicSearchCO topicSearchCO = new TopicSearchCO(id: id)

        if(session.user)
        {
            if(!(session.user.isAdmin || session.user.equals(User.load(id)))){
                topicSearchCO.visibility = Visibility.PUBLIC
            }
        }
        else
            topicSearchCO.visibility = Visibility.PUBLIC

        List<TopicVo> createdTopics = topicService.search(topicSearchCO)

        render(template:'/topic/list', model: [topics: createdTopics])
    }

    def subscriptions(Long id){

        TopicSearchCO topicSearchCO = new TopicSearchCO(id: id)

        if(session.user)
        {
            if(!(session.user.isAdmin || session.user.equals(User.load(id)))){
                topicSearchCO.visibility = Visibility.PUBLIC
            }
        }
        else
            topicSearchCO.visibility = Visibility.PUBLIC

        List<TopicVo> subscribedTopics = subscriptionService.search(topicSearchCO)

        render(template:'/topic/list', model: [topics: subscribedTopics])

    }
}
