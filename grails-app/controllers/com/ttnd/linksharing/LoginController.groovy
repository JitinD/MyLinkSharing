package com.ttnd.linksharing

import CO.UserCO
import VO.PostVO
import com.ttnd.linksharing.constants.Constants

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
            } else {
                flash.loginError = g.message(code: "not.active.user")
            }
        } else {
            flash.loginError = g.message(code: "not.found.user")
        }

        redirect(action: "index")
    }

    def register(UserCO user) {

        List<PostVO> topPosts = Resource.getTopPosts()
        List<PostVO>recentPosts = Resource.getRecentPosts()

        if(user.hasErrors())
        {
            flash.error = g.message(code: "not.valid.user")

            render (view: "index", model: [topPosts : topPosts, recentPosts: recentPosts, user: user])
        }
        else
        {
            User newUser = user.properties
            newUser.isActive = true
            newUser.isAdmin = false

            if(!params.pic.empty)
            {
                if(Constants.IMAGE_CONTENT_TYPE.contains(params.pic.contentType))
                    newUser.photo = params.pic.bytes
            }

            if(newUser.saveInstance())
            {
                log.info "User saved successfully"
                session.user = newUser
                redirect(controller: "user", action: "index")
            }
            else{
                flash.error = g.message(code: "not.saved.user")
                render (view: "index", model: [topPosts : topPosts, recentPosts: recentPosts, user: newUser])
            }

        }
    }

    def logout() {
        session.invalidate()
        redirect(action:'index')
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
