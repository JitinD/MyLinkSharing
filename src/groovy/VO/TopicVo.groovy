package VO

import com.ttnd.linksharing.User
import enums.Visibility

/**
 * Created by jitin on 24/2/16.
 */
class TopicVo {
    Long id
    String name
    Visibility visibility
    Integer count
    User createdBy


    @Override
    String toString()
    {
     return "$id , $name, $visibility, $count, $createdBy"
    }
}
