package com.ttnd.linksharing

class UserController {

    def index()
    {
        render (view: 'index' , model: [user : session.user, subscribedTopics : session.user.subscribedTopics, trendingTopics : Topic.getTrendingTopics()])
    }
}
