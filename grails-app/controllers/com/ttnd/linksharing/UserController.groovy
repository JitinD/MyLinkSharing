package com.ttnd.linksharing

class UserController {
    //String domainName = "com.ttnd.linksharing.User"
    //String userName = "normal"
    //String password = "password"
    //Map domainParams = [userName:"normal", password:"defaultPassword"]

    def index()
    {
        render (view: 'index' , model: [topicNames : session.user.subscribedTopics, topicVoList : Topic.getTrendingTopics()])
    }
}
