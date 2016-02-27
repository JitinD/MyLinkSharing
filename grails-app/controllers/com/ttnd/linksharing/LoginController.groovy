package com.ttnd.linksharing

import com.ttnd.linksharing.constants.Constants

class LoginController {

    def index() {
        if (session.user) {
            forward(controller: "user", action: "index")
        } else {
            //render "something"
            def result = Resource.getTopPosts()
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

    def register(User user) {

        //User user = User.list()
        render(view: 'register', model: [user: user])

        /*User newUser = new User('userName': 'normal', emailID: 'newUser@mail.com', password: "newUserPassword", confirmPassword: "newUserPassword", firstName: 'normal', lastName: 'user', isAdmin: false, isActive: true)

        if (newUser.saveInstance()) {
            render "New user added. ~SUCCESS~"
        } else {
            flash.message = "${newUser} could not be added, ${newUser.errors.allErrors}"
            render "${newUser.errors.allErrors.collect { message(error: it) }}"
        }*/
    }

    def logout() {
        session.invalidate()
        //forward(action: "index")
        redirect(action:'index')
    }
}
