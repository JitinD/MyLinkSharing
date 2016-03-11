package com.ttnd.linksharing

import CO.TopicSearchCO
import VO.TopicVo
import grails.transaction.Transactional

@Transactional
class TopicService {

    def serviceMethod() {

    }

    List<Topic> search(TopicSearchCO topicSearchCO) {
        List<TopicVo> createdTopicsList = []

        if (topicSearchCO.id) {
            User user = topicSearchCO.getUser()

            if(topicSearchCO.visibility){

                List<Topic> topicList = Topic.createCriteria().list(max: 5) {
                    eq('createdBy.id', topicSearchCO.id)

                    if(topicSearchCO.visibility)
                        eq('visibility', topicSearchCO.visibility)
                }

                topicList.each {
                    topic -> createdTopicsList.add(new TopicVo(id: topic.id, name: topic.name, visibility: topic.visibility, createdBy: topic.createdBy))
                }
            }
        }

        return createdTopicsList
    }
}
