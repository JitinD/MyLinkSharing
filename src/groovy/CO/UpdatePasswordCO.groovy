package CO

import com.ttnd.linksharing.User
import grails.validation.Validateable

@Validateable
class UpdatePasswordCO {

    Long id
    String oldPassword
    String password
    String confirmPassword

    static constraints = {
        importFrom(User, include: ['password', 'confirmPassword'])

        oldPassword(validator: {
            value, user ->
                if (!user.id) {
                    if(!user.id && value.equals(user.getUser()?.password))
                        return "Enter the old password correctly."
                }
        })
    }

    User getUser(){
        return User.get(id)
    }
}
