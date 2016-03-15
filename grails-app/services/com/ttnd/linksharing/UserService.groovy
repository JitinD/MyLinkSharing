package com.ttnd.linksharing

import grails.transaction.Transactional

@Transactional
class UserService {

    def emailService

    def sendUnreadItemsEmail(){
        List<User> userList = getUsersWithUnreadItems()

        userList.each {
            user -> emailService.sendUnreadResourcesEmail(user, getUnreadResourcesForUser(user))
        }
    }

    List<User> getUsersWithUnreadItems(){

        return Resource.usersWithUnreadResources()
    }

    List<Resource> getUnreadResourcesForUser(User user){

        return user.unreadResources()
    }
}
