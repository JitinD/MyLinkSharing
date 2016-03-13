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
import enums.Visibility


class UserController {

    def assetResourceLocator
    def topicService
    def subscriptionService
    def emailService
    def messageSource

    def index(SearchCO searchCO) {

        searchCO.max = searchCO.max ?: 5
        searchCO.offset = searchCO.offset ?:0

        render(view: 'index', model: [user                : session.user?.getInfo(), subscribedTopics: session.user?.subscribedTopics,
                                      trendingTopics      : Topic.getTrendingTopics(), inboxPosts: session.user?.getInboxPosts(searchCO),
                                      subscribedTopicsList: session.user?.getSubscribedTopicsList(), searchCO: searchCO, totalInboxPosts: session.user.getInboxPostsCount()])
    }

    def profile(ResourceSearchCO resourceSearchCO) {

        User user = User.get(resourceSearchCO.id)

        if (session.user) {
            if (!(session.user.isAdmin || session.user.equals(user))) {
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

        if (session.user) {
            if (!(session.user.isAdmin || session.user.equals(User.load(id)))) {
                topicSearchCO.visibility = Visibility.PUBLIC
            }
        } else
            topicSearchCO.visibility = Visibility.PUBLIC

        List<TopicVo> createdTopics = topicService.search(topicSearchCO)

        render(template: '/topic/list', model: [topics: createdTopics])
    }

    def subscriptions(Long id) {

        TopicSearchCO topicSearchCO = new TopicSearchCO(id: id)

        if (session.user) {
            if (!(session.user.isAdmin || session.user.equals(User.load(id)))) {
                topicSearchCO.visibility = Visibility.PUBLIC
            }
        } else
            topicSearchCO.visibility = Visibility.PUBLIC

        List<TopicVo> subscribedTopics = subscriptionService.search(topicSearchCO)

        render(template: '/topic/list', model: [topics: subscribedTopics])

    }

    def list(UserSearchCO userSearchCO) {

        if (session.user) {
            if (session.user.isAdmin) {

                List<User> users = User.search(userSearchCO).list(max: userSearchCO.max, sort: userSearchCO.sort, order: userSearchCO.order)

                List<UserVO> usersList = users?.collect {
                    user ->
                        new UserVO(userId: user.id, userName: user.userName, emailID: user.emailID, firstName: user.firstName,
                                lastName: user.lastName, isActive: user.isActive)
                }

                render(view: "/user/list", model: [usersList: usersList])
            } else
                redirect(controller: "login", action: "index")
        } else
            redirect(controller: "login", action: "index")

    }

    def toggleActive(Long id) {
        if (session.user) {

            if (session.user.isAdmin) {

                User user = User.get(id)

                if (user) {
                    if (user.isAdmin) {
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
        } else {
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

                    emailService.sendMail(emailDTO)
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

    def save(UserUpdateCO user) {

        if (session.user) {

            if (user.hasErrors()) {
                render user.errors
                flash.error = "User details are invalid."
            } else {

                User sessionUser = session.user

                sessionUser.properties = user.properties

                if (!params.pic.empty)
                    sessionUser.photo = params.pic.bytes

                if (sessionUser.saveInstance()) {
                    flash.message = "User saved successfully"
                    session.user = sessionUser
                } else {
                    flash.error = "User could not be saved."
                }
            }

            redirect(controller: "user", action: "edit")
        } else
            redirect(controller: "login", action: "index")
    }

    def edit() {
        if (session.user) {
            UserVO user = session.user.getInfo()

            render(view: "edit", model: [user: user])
        } else
            redirect(controller: "login", action: "index")
    }

    def updatePassword(UpdatePasswordCO updatePasswordCO) {

        User user = session.user

        if (user) {

            user.password = updatePasswordCO.password
            user.confirmPassword = updatePasswordCO.password

            if (user.saveInstance()) {
                flash.message = "Password updated successfully."
                session.user = user
            } else {
                flash.error = "Password could not be updated."
            }


            flash.error = "user not found"
            redirect(controller: "user", action: "edit")

        } else
            redirect(controller: "login", action: "index")
    }
}
