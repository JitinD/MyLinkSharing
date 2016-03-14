package com.ttnd.linksharing

import CO.SearchCO
import CO.TopicSearchCO
import CO.UserSearchCO
import VO.PostVO
import VO.TopicVo
import VO.UserVO
import com.ttnd.linksharing.constants.Constants

class User {

    String userName;
    String password;
    String confirmPassword;
    String firstName;
    String lastName;
    String emailID;
    byte[] photo;
    Boolean isAdmin;
    Boolean isActive
    Date dateCreated;
    Date lastUpdated;

    static transients = ['confirmPassword', 'subscribedTopics', 'subscribedTopicsList'];

    static constraints = {
        userName(blank: false, unique: true)
        emailID(unique: true, blank: false, nullable: false, email: true)
        password(nullable: false, blank: false, minSize: 5)

        confirmPassword(bindable: true, nullable: true, blank: true, validator: {
            value, user ->
                if (!user.id) {
                    return value && value == user.password
                }
        })

        firstName(nullable: false, blank: false)
        lastName(nullable: false, blank: false)
        photo(nullable: true)
        isActive(nullable: true)
        isAdmin(nullable: true)
        /*contentType(bindable: true, validator: {
            value -> return Constants.IMAGE_CONTENT_TYPE.contains(value)
        })*/
    }

    String getName() {
        return [firstName, lastName].findAll { it }.join(' ');
    }

    static mapping = {
        photo sqlType: 'longblob'
        sort id: 'desc'
    }

    static hasMany = [topics: Topic, subscriptions: Subscription, readingItems: ReadingItem, resources: Resource, resourceRatings: ResourceRating]

    static namedQueries = {
        search {
            UserSearchCO userSearchCO ->

                if (userSearchCO.q) {

                    or
                    {
                        ilike('firstName', "%${userSearchCO.q}%")
                        ilike('lastName', "%${userSearchCO.q}%")
                        ilike('emailID', "%${userSearchCO.q}%")
                        ilike('userName', "%${userSearchCO.q}%")

                    }
                }

                if (userSearchCO.isActive != null) {
                    eq('isActive', userSearchCO.isActive)
                }

                eq('isAdmin', false)
        }

    }

    String getConfirmPassword() {
        return confirmPassword
    }


    String toString() {
        return userName ?: ""
    }


    public List<TopicVo> getSubscribedTopics() {
        List<TopicVo> subscribedTopicsList = []

        List<Topic> topicList = Subscription.createCriteria().list(max: 5) {
            projections {
                property('topic')
            }
            eq('user.id', id)
        }

        topicList.each {
            topic -> subscribedTopicsList.add(new TopicVo(id: topic.id, name: topic.name, visibility: topic.visibility, createdBy: topic.createdBy))
        }

        return subscribedTopicsList
    }


    public List<TopicVo> getSubscribedTopicsList(TopicSearchCO topicSearchCO) {
        List<TopicVo> subscribedTopicsList = []

        List<Topic> topicList = Subscription.createCriteria().list(){
            projections {
                property('topic')
            }
            eq('user.id', id)
        }

        topicList.each {
            topic -> subscribedTopicsList.add(new TopicVo(id: topic.id, name: topic.name, visibility: topic.visibility, createdBy: topic.createdBy))
        }

        return subscribedTopicsList
    }

    public getSubscription(Long topicId) {

        Topic topic = Topic.get(topicId)

        Subscription subscription = Subscription.findByUserAndTopic(this, topic)

        return subscription
    }

    Boolean equals(User user) {

        if (this.id == user.id)
            return true

        return false
    }

    public User saveInstance() {

        User user = this

        user.validate()

        if (user.hasErrors()) {
            log.error("User has validation errors : ${user.errors}")

            return null
        } else {
            user.save(failOnError: true, flush: true)
            log.info "${user} saved successfully"

            return user
        }
    }

    public List<PostVO> getInboxPosts(SearchCO searchCO) {
        List<PostVO> inboxPostVOs = []

        def inboxPosts = ReadingItem.createCriteria().list(max: searchCO.max, offset: searchCO.offset) {
            projections
                    {
                        property('resource')
                        property('isRead')
                    }
            eq('user', this)
            createAlias('resource', 'r')
        }

        inboxPosts.each {
            post ->
                inboxPostVOs.add(new PostVO(userId: post[0].createdBy.id, topicId: post[0].topic.id, resourceId: post[0].id,
                        isRead: post[1], user: post[0].createdBy.name, userName: post[0].createdBy.userName,
                        topicName: post[0].topic.name, description: post[0].description, url: post[0].class.equals(LinkResource) ? post[0].url : null,
                        filePath: post[0].class.equals(DocumentResource) ? post[0].filePath : null, createdDate: post[0].dateCreated))
        }

        return inboxPostVOs
    }

    public UserVO getInfo() {
        UserVO userVO = new UserVO(userId: this.id, name: this.name, userName: this.userName, emailID: this.emailID,
                firstName: this.firstName, lastName: this.lastName, photo: this.photo)

        return userVO
    }

    public Boolean canDeleteResource(Long resourceId) {
        Resource resource = Resource.read(resourceId)

        if (this.isAdmin || (resource.createdBy.id == this.id))
            return true

        return false
    }

    public Integer getScore(Long resourceId) {
        Resource resource = Resource.load(resourceId)
        User user = this

        ResourceRating resourceRating = ResourceRating.findByUserAndResource(user, resource)

        if (resourceRating)
            return resourceRating.score
        else
            return 1 //Temporary
    }

    public Boolean isSubscribed(Long topicId) {
        Topic topic = Topic.get(topicId)
        User user = this

        Subscription subscription = Subscription.createCriteria().get {
            eq('user', user)
            eq('topic', topic)
        }

        if (subscription)
            return true

        return false
    }

    public List<PostVO> getCreatedPosts() {
        List<PostVO> createdPostVOs = []

        def createdPosts = Resource.createCriteria().list {
            eq('createdBy.id', id)
        }

        createdPosts.each {
            post ->
                createdPostVOs.add(new PostVO(userId: post.createdBy.id, topicId: post.topic.id, resourceId: post.id,
                        user: post.createdBy.name, userName: post.createdBy.userName, topicName: post.topic.name,
                        description: post.description, url: post.class.equals(LinkResource) ? post.url : null,
                        filePath: post.class.equals(DocumentResource) ? post.filePath : null, createdDate: post.dateCreated))
        }

        return createdPostVOs
    }

    public Integer getInboxPostsCount(){
        return ReadingItem.countByUser(this)
    }

    public Integer getCreatedTopicsCount(){
        return Topic.countByCreatedBy(this)
    }

    public Integer getSubscribedTopicsCount(){
        return Subscription.countByUser(this)
    }

}