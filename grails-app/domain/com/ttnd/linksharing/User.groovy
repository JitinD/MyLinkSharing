package com.ttnd.linksharing

import VO.PostVO
import VO.TopicVo
import VO.UserVO

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

    static constraints = {
        userName(blank: false)
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

    }

    static transients = ['confirmPassword', 'subscribedTopics', 'subscribedTopicsList'];

    String getName() {
        return [firstName, lastName].findAll { it }.join(' ');
    }

    static mapping = {
        photo sqlType: 'longblob'
        sort id: 'desc'
    }

    static hasMany = [topics: Topic, subscriptions: Subscription, readingItems: ReadingItem, resources: Resource, resourceRatings: ResourceRating]

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        User user = (User) o

        if (emailID != user.emailID) return false
        if (id != user.id) return false
        if (userName != user.userName) return false

        return true
    }

    int hashCode() {
        int result
        result = userName.hashCode()
        result = 31 * result + emailID.hashCode()
        result = 31 * result + (id != null ? id.hashCode() : 0)
        return result
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


    public List<TopicVo> getSubscribedTopicsList() {
        List<TopicVo> subscribedTopicsList = []

        List<Topic> topicList = Subscription.createCriteria().list{
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

    public List<PostVO> getInboxPosts() {
        List<PostVO> inboxPostVOs = []

        def inboxPosts = ReadingItem.createCriteria().list {
            projections
                    {
                        property('resource')
                        property('isRead')
                    }

            eq('isRead', false)
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
        UserVO userVO = new UserVO(userId: this.id, name: this.name, userName: this.userName, emailID: this.emailID, photo: this.photo)

        return userVO
    }

    public Boolean canDeleteResource(Long resourceId)
    {
        Resource resource = Resource.read(resourceId)

        if(this.isAdmin || (resource.createdBy.id == this.id) )
            return true

        return false
    }

    public Integer getScore(Long resourceId)
    {
        Resource resource = Resource.load(resourceId)
        User user = this

        ResourceRating resourceRating = ResourceRating.findByUserAndResource(user, resource)

        if(resourceRating)
            return resourceRating.score
        else
            return 1 //Temporary
    }

    public Boolean isSubscribed(Long topicId)
    {
        Topic topic = Topic.get(topicId)
        User user = this

        Subscription subscription = Subscription.createCriteria().get {
            eq('user', user)
            eq('topic', topic)
        }

        if(subscription)
            return true

        return  false
    }
}