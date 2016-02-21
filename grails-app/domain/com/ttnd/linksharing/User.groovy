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


    String toString()
    {
        return userName
    }

    public static User save(User user) {

        user.validate()

        if (user.hasErrors())
        {
            user.errors.each {
                log.error("error saving user", it)
            }

            return null
        }
        else
        {
            user.save(failOnError: true, flush: true)

            return user
        }
    }
}