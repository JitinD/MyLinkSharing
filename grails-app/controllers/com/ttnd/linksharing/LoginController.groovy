package com.ttnd.linksharing

import CO.UserCO
import VO.PostVO
import com.ttnd.linksharing.constants.Constants

class LoginController {

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
                flash.error = "User account is not active"
                //render flash.error
            }
        } else {
            flash.error = "User not found"
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

            if(newUser.saveInstance())
            {
                flash.message = "User saved successfully"
                session.user = newUser
                redirect(controller: "user", action: "index")
            }
            else
                flash.error = "User could not be saved."
        }
    }

    def logout() {
        session.invalidate()
        //forward(action: "index")
        redirect(action:'index')
    }
}
