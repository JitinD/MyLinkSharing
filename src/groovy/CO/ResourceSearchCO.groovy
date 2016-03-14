package CO

import com.ttnd.linksharing.User
import enums.Visibility
import grails.validation.Validateable

@Validateable
class ResourceSearchCO extends SearchCO{
    Long id
    Long topicId
    Visibility visibility
    Boolean global

    public User getUser(){
        User user = User.get(id)

        return user
    }
}
