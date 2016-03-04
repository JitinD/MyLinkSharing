package com.ttnd.linksharing

class UserController {

    def index()
    {
        render (view: 'index' , model: [user : session.user?.getInfo(), subscribedTopics : session.user?.subscribedTopics, trendingTopics : Topic.getTrendingTopics(), inboxPosts: session.user?.getInboxPosts()])
    }
}
