package CO

import com.ttnd.linksharing.User
import grails.validation.Validateable

@Validateable
class UpdatePasswordCO {

    Long id
    String oldPassword
    String password

    static constraints = {
        importFrom(User, include: ['password'])

        oldPassword(validator: {
            value, user ->
                if (!user.id) {
                    if(!user.id && value.equals(user.getUser()?.password))
                        return "Doesn't match with current password."
                }
        })
    }

    User getUser(){
        return User.get(id)
    }
}
