package com.ttnd.linksharing

class UserController {

    def index()
    {
        render (view: 'index' , model: [topicNames : session.user.subscribedTopics, topicVoList : Topic.getTrendingTopics()])
    }
}
