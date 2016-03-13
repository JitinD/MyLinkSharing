package CO

import com.ttnd.linksharing.User
import grails.validation.Validateable

@Validateable
class UserUpdateCO {

    Long id
    String userName;
    String firstName;
    String lastName;
    byte[] photo;


    static constraints = {
        importFrom User
    }

    public User getUser(){
        return User.get(id)
    }
}
