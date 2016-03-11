package CO

import com.ttnd.linksharing.User
import enums.Visibility

/**
 * Created by jitin on 11/3/16.
 */
class TopicSearchCO extends SearchCO {
    Long id
    Visibility visibility

    public User getUser(){
        User user = User.get(id)

        return user
    }
}
