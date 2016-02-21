package com.ttnd.linksharing

class LoginController {

    def index() {
        if (session.user) {
            forward(controller: "user", action: "index")
        } else
            render "You must login. ~Failure~"
    }

    def loginHandler(String userName, String password) {

        User user = User.findByUserNameAndPassword(userName, password)

        if (user) {
            if (user.isActive) {
                session.user = user
                redirect(action: "index")
            }
            else{
                flash.error =  "User account is not active"
                render flash.error
            }
        }
        else{
            flash.error = "User not found"
            render flash.error
        }
    }

    def logout() {
        session.invalidate()
        forward(action: "index")
    }
}
