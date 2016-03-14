package CO

import com.ttnd.linksharing.User
import enums.Visibility
import grails.validation.Validateable

@Validateable
class TopicSearchCO extends SearchCO {
    Long id
    Visibility visibility

    public User getUser(){
        User user = User.get(id)

        return user
    }
}
