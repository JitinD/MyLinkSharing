package com.ttnd.linksharing

import CO.ResourceSearchCO
import CO.SearchCO
import CO.TopicSearchCO
import CO.UserSearchCO
import DTO.EmailDTO
import Utility.Util
import VO.PostVO
import VO.TopicVo
import VO.UserVO
import enums.Visibility
import org.apache.bcel.verifier.exc.Utility

class UserController {

    def assetResourceLocator
    def topicService
    def subscriptionService
    def emailService
    def messageSource

    def index() {

        render(view: 'index', model: [user                : session.user?.getInfo(), subscribedTopics: session.user?.subscribedTopics,
                                      trendingTopics      : Topic.getTrendingTopics(), inboxPosts: session.user?.getInboxPosts(),
                                      subscribedTopicsList: session.user?.getSubscribedTopicsList()])
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
                        flash.message = "User active status changed"
                    } else
                        flash.error = "User active status could not be changed"
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
                String subject = "Forgot password request"
                String newPassword = Util.randomPassword

                EmailDTO emailDTO = new EmailDTO(to: to, subject: subject, model: [newPassword: newPassword])

                user.password = newPassword

                if (user.saveInstance()) {

                    emailService.sendMail(emailDTO)
                    flash.message = "Mail sent with new password."
                } else {
                    flash.error = "Mail could not be sent."
                }
            } else {
                flash.error = "The user account corresponding to the entered email address is inactive."
            }
        } else {
            flash.error = "The email id doesn't belong to a registered user."
        }

        redirect(controller: "login", action: "index")
    }


}
