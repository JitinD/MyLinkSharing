package CO

import com.ttnd.linksharing.User
import grails.validation.Validateable

/**
 * Created by jitin on 12/3/16.
 */

@Validateable
class UpdatePasswordCO {

    Long id
    String oldPassword
    String password
    String confirmPassword

    static constraints = {
        importFrom User

        oldPassword(bindable: true, nullable: true, blank: true, validator: {
            value, user ->
                if (!user.id) {
                    return value && value == user.password
                }
        })

        confirmPassword(bindable: true, nullable: true, blank: true, validator: {
            value, user ->
                if (!user.id) {
                    return value && value == user.password
                }
        })
    }

    User getUser(){
        return User.get(id)
    }
}
