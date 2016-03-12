package com.ttnd.linksharing

import CO.UserCO
import VO.PostVO
import com.ttnd.linksharing.constants.Constants
import grails.converters.JSON

class LoginController {

    def mailService
    def index() {
        if (session.user) {
            forward(controller: "user", action: "index")
        } else {

            List<PostVO> topPosts = Resource.getTopPosts()
            List<PostVO>recentPosts = Resource.getRecentPosts()
            render (view: "index", model: [topPosts : topPosts, recentPosts: recentPosts])
        }
    }


    def loginHandler(String userName, String password) {

        User user = User.findByUserNameAndPassword(userName, password)

        if (user) {
            if (user.isActive) {
                session.user = user
                redirect(action: "index")
            } else {
                flash.error = g.message(code: "not.active.user")
                redirect controller: "login"
            }
        } else {
            flash.error = g.message(code: "not.found.user")
            redirect controller: "login"
            //render flash.error
        }
    }

    def register(UserCO user) {

        List<PostVO> topPosts = Resource.getTopPosts()
        List<PostVO>recentPosts = Resource.getRecentPosts()

        if(user.hasErrors())
        {
            flash.error = "User data invalid."

            render (view: "index", model: [topPosts : topPosts, recentPosts: recentPosts, user: user])
        }
        else
        {
            User newUser = user.properties
            newUser.isActive = true

            if(!params.pic.empty)
                newUser.photo = params.pic.bytes

            if(newUser.saveInstance())
            {
                flash.message = "User saved successfully"
                session.user = newUser
                redirect(controller: "user", action: "index")
            }
            else{
                flash.error = "User could not be saved."
                render (view: "index", model: [topPosts : topPosts, recentPosts: recentPosts, user: newUser])
            }

        }
    }

    def logout() {
        session.invalidate()
        redirect(action:'index')
    }

    def testMail()
    {
        mailService.sendMail {
            to "jitin.dominic@tothenew.com"
            subject "Hello Jitin"
            body 'this is some text'
        }

        render "Mail sent"

    }

    def validateEmail(){

        Integer numUser = User.countByEmailID(params.emailID)
        log.info params.emailID

        Boolean result = numUser ? false : true

        render result
    }

    def validateUserName(){
        System.err.println("................$params")
        Integer numUser = User.countByUserName(params.userName)
        log.info params.userName
        Boolean result = numUser ? false : true

        render result

    }

}
