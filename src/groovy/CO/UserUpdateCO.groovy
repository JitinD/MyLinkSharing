package CO

import com.ttnd.linksharing.User
import grails.validation.Validateable

/**
 * Created by jitin on 12/3/16.
 */

@Validateable
class UserUpdateCO {

    String userName;
    String firstName;
    String lastName;
    byte[] photo;


    static constraints = {
        importFrom User
    }
}
