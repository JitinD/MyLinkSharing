package com.ttnd.linksharing

class UserController {

    def index()
    {
        render "Dashboard of user : ${session.user.userName}"
    }
}
