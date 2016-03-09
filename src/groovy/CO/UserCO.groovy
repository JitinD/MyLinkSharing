package CO

import grails.validation.Validateable

@Validateable
class UserCO {

    String emailID
    String userName;
    String password;
    String confirmPassword;
    String firstName;
    String lastName;
    byte[] photo;


    static constraints = {
        userName(blank: false, null: false)
        emailID(unique: true, blank: false, nullable: false, email: true)
        password(nullable: false, blank: false, minSize: 5)

        confirmPassword(bindable: true, nullable: true, blank: true, validator: {
            value, user ->
                return value && value == user.password

        })

        firstName(nullable: false, blank: false)
        lastName(nullable: false, blank: false)
        photo(nullable: true)
    }
}