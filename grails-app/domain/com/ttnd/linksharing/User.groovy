package com.ttnd.linksharing

import VO.TopicVo

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

        confirmPassword(bindable:true, nullable: true, blank: true, validator:{
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

    static transients = ['confirmPassword', 'subscribedTopics'];

    String getName() {
        return [firstName, lastName].findAll { it }.join(' ');
    }

    static mapping = {
        photo sqlType: 'longblob'
        sort id : 'desc'
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


    String getConfirmPassword()
    {
        return confirmPassword
    }


    String toString() {
        return userName ?: ""
    }


    public List<TopicVo> getSubscribedTopics()
    {
        List<TopicVo> subscribedTopicsList = []

        List<Topic> topicList = Subscription.createCriteria().list(max:5){
            projections {
                property('topic')
            }
            eq('user.id', id)
        }

        topicList.each {
            topic -> subscribedTopicsList.add(new TopicVo(id: topic.id, name: topic.name, visibility: topic.visibility, createdBy: topic.createdBy))
        }

        return  subscribedTopicsList
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

}