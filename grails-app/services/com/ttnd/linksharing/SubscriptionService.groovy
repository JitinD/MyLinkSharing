package com.ttnd.linksharing

import CO.TopicSearchCO
import VO.TopicVo
import grails.transaction.Transactional

@Transactional
class SubscriptionService {

    def serviceMethod() {

    }

    List<TopicVo> search(TopicSearchCO topicSearchCO){

        if(topicSearchCO.id)
        {
            User user = topicSearchCO.getUser()

            return user.getSubscribedTopicsList()
        }
    }
}
