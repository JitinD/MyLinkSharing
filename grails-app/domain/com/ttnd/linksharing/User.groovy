package com.ttnd.linksharing

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


/*
        confirmPassword(validator:{
            value, user ->
                if (user.id && User.exists(user.id)) {
                    confirmPassword nullable: true, blank: true
                }
                else
                {
                    return value && value == password
                }
        })
*/


        firstName(nullable: false, blank: false)
        lastName(nullable: false, blank: false)
        photo(nullable: true)
        isActive(nullable: true)
        isAdmin(nullable: true)

    }

    static transients = ['confirmPassword'];

    String getName() {
        return [firstName, lastName].findAll { it }.join(' ');
    }

    static mapping = {
        photo sqlType: 'longblob'
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