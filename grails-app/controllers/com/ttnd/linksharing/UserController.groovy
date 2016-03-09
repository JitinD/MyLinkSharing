package com.ttnd.linksharing

class UserController {

    def assetResourceLocator

    def index() {
        render(view: 'index', model: [user: session.user?.getInfo(), subscribedTopics: session.user?.subscribedTopics,
                                      trendingTopics: Topic.getTrendingTopics(), inboxPosts: session.user?.getInboxPosts(), subscribedTopicsList: session.user?.getSubscribedTopicsList()])
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
}
