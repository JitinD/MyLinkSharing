package CO

import com.ttnd.linksharing.User
import enums.Visibility

class ResourceSearchCO extends SearchCO{
    Long id
    Long topicId
    Visibility visibility

    public User getUser(){
        User user = User.get(id)

        return user
    }
}
