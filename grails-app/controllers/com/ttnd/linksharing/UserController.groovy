package com.ttnd.linksharing

import CO.ResourceSearchCO
import CO.SearchCO
import CO.TopicSearchCO
import CO.UpdatePasswordCO
import CO.UserSearchCO
import CO.UserUpdateCO
import DTO.EmailDTO
import Utility.Util
import VO.PostVO
import VO.TopicVo
import VO.UserVO
import com.ttnd.linksharing.constants.Constants
import enums.Visibility
import grails.plugin.springsecurity.annotation.Secured

@Secured(['permitAll'])
class UserController {

    def assetResourceLocator
    def topicService
    def subscriptionService
    def emailService
    def messageSource
    def springSecurityService

    @Secured(['ROLE_NORMAL'])
    def index(SearchCO searchCO) {

        searchCO.max = searchCO.max ?: 5
        searchCO.offset = searchCO.offset ?: 0
        User user = User.loggedInUser()
        render(view: 'index', model: [user                : user?.getInfo(), subscribedTopics: user?.subscribedTopics,
                                      trendingTopics      : Topic.getTrendingTopics(), inboxPosts: user?.getInboxPosts(searchCO),
                                      subscribedTopicsList: user?.getSubscribedTopicsList(), searchCO: searchCO, totalInboxPosts: user.getInboxPostsCount()])
    }

    def profile(ResourceSearchCO resourceSearchCO) {

        User user = User.get(resourceSearchCO.id)

        if (User.loggedInUser()) {
            if (!(User.loggedInUser().isAdmin() || User.loggedInUser().equals(user))) {
                resourceSearchCO.visibility = Visibility.PUBLIC
            }
        } else
            resourceSearchCO.visibility = Visibility.PUBLIC

        List<Resource> resources = Resource.search(resourceSearchCO).list()
        List<PostVO> createdPosts = resources?.collect { Resource.getPostInfo(it.id) }

        render(view: 'profile', model: [createdPosts: createdPosts, user: user.getInfo()])
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

    def topics(Long id) {

        TopicSearchCO topicSearchCO = new TopicSearchCO(id: id)

        if (User.loggedInUser()) {
            if (!(User.loggedInUser().isAdmin() || User.loggedInUser().equals(User.load(id)))) {
                topicSearchCO.visibility = Visibility.PUBLIC
            }
        } else
            topicSearchCO.visibility = Visibility.PUBLIC

        List<TopicVo> createdTopics = topicService.search(topicSearchCO)

        render(template: '/topic/list', model: [topics: createdTopics])
    }

    def subscriptions(Long id) {

        TopicSearchCO topicSearchCO = new TopicSearchCO(id: id)

        if (User.loggedInUser()) {
            if (!(User.loggedInUser().isAdmin() || User.loggedInUser().equals(User.load(id)))) {
                topicSearchCO.visibility = Visibility.PUBLIC
            }
        } else
            topicSearchCO.visibility = Visibility.PUBLIC

        List<TopicVo> subscribedTopics = subscriptionService.search(topicSearchCO)

        render(template: '/topic/list', model: [topics: subscribedTopics])

    }

    @Secured(['ROLE_ADMIN'])
    def list(UserSearchCO userSearchCO) {

        userSearchCO.max = Constants.NUMBER_RECORDS_IN_LIST
        userSearchCO.offset = userSearchCO.offset ?: 0

        if (User.loggedInUser()) {
            if (User.loggedInUser().isAdmin()) {

                List<User> users = User.search(userSearchCO).list(max: userSearchCO.max, offset: userSearchCO.offset, sort: userSearchCO.sort, order: userSearchCO.order)

                List<UserVO> usersList = users?.collect {
                    user ->
                        new UserVO(userId: user.id, username: user.username, emailID: user.emailID, firstName: user.firstName,
                                lastName: user.lastName, isActive: user.isActive)
                }

                render(view: "/user/list", model: [usersList: usersList, userSearchCO: userSearchCO, totalUsersCount: User.getTotalUsersCount()])
            } else
                redirect(controller: "login", action: "index")
        }
    }

    @Secured(['ROLE_ADMIN'])
    def toggleActive(Long id) {
        if (User.loggedInUser()) {

            if (User.loggedInUser().isAdmin()) {

                User user = User.get(id)

                if (user) {
                    if (user.isAdmin()) {
                        flash.error = "Admin active status cannot be changed."
                    } else
                        user.isActive = !(user.isActive)

                    if (user.saveInstance()) {
                        flash.message = g.message(code: "is.changed.activeStatus")
                    } else
                        flash.error = g.message(code: "not.changed.activeStatus")
                } else
                    flash.error = "User not found."

                redirect(controller: "user", action: "list")
            } else
                redirect(controller: "login", action: "index")
        }
    }


    def forgotPassword(String emailID) {

        User user = User.findByEmailID(emailID)

        if (user) {
            if (user.isActive) {
                String to = emailID
                String subject = g.message(code: "forgot.password.request")
                String newPassword = Util.randomPassword

                EmailDTO emailDTO = new EmailDTO(to: to, subject: subject, model: [newPassword: newPassword])

                user.password = newPassword

                if (user.saveInstance()) {

                    def ctx = startAsync()

                    ctx.start {
                        emailService.sendMail(emailDTO)
                    }

                    ctx.complete()

                    flash.message = g.message(code: "is.sent.email")
                } else {
                    flash.error = g.message(code: "not.sent.email")
                }
            } else {
                flash.error = g.message(code: "not.active.user")
            }
        } else {
            flash.error = "The email id doesn't belong to a registered user."
        }

        redirect(controller: "login", action: "index")
    }

    @Secured(['ROLE_NORMAL'])
    def save(UserUpdateCO updateUser) {

        if (User.loggedInUser()) {

            User sessionUser = User.get(User.loggedInUser().id)

            sessionUser.firstName = updateUser.firstName
            sessionUser.lastName = updateUser.lastName
            sessionUser.username = updateUser.username

            if (!params.pic.empty)
                sessionUser.photo = params.pic.bytes

            if (sessionUser.saveInstance()) {
                flash.message = "User saved successfully"
                session.user = sessionUser
            } else {
                flash.error = "User could not be saved."
            }

            redirect(controller: "user", action: "edit")
        }
    }

    @Secured(['ROLE_NORMAL'])
    def edit() {
        if (User.loggedInUser()) {
            UserVO user = User.loggedInUser().getInfo()
            render(view: "edit", model: [user: user])
        }
    }


    @Secured(['ROLE_NORMAL'])
    def updatePassword(UpdatePasswordCO updatePasswordCO) {

        if (User.loggedInUser()) {

            User user = User.get(User.loggedInUser().id)

            if (updatePasswordCO.oldPassword == user.password) {

                if (updatePasswordCO.password && updatePasswordCO.password.size() >= 5) {
                    if ((updatePasswordCO.password == updatePasswordCO.confirmPassword)) {

                        user.password = updatePasswordCO.password
                        user.confirmPassword = updatePasswordCO.confirmPassword

                        if (user.saveInstance()) {
                            flash.message = "Password updated successfully."
                            session.user = user
                        } else {
                            flash.error = "Password could not be updated."
                        }
                    } else
                        flash.error = "Password and confirm password do not match."

                } else {
                    flash.error = "Password should be more than 5 characters long."
                }
            } else
                flash.error = "Current and old passwod field do not match."

            redirect(controller: "user", action: "edit")
        }
    }

    @Secured(['ROLE_NORMAL'])
    def validateEmailForInvitation() {

        Integer numUser = User.countByEmailID(params.emailID)

        Boolean result = numUser ? true : false

        render result
    }

    @Secured(['ROLE_NORMAL'])
    def validateOldPassword() {

        User user = User.loggedInUser()

        Boolean result = (user.password == params.oldPassword)

        render result
    }

}