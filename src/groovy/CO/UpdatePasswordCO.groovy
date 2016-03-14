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
        importFrom(User, include: ['password'])

        oldPassword(validator: {
            value, UpdatePasswordCO updatePasswordCO ->

                if(!(value && value.equals(updatePasswordCO.getUser()?.password))){
                    return  "Enter the current password correctly."
                }
        })

        confirmPassword(validator: {
            value, UpdatePasswordCO updatePasswordCO ->

                if(!(value && value.equals(updatePasswordCO.password))){
                    return "Confirm password and new password do not match."
                }
        })
    }

    User getUser(){
        return User.get(id)
    }
}
