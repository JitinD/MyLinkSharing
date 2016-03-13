package com.ttnd.linksharing

import CO.TopicSearchCO
import VO.TopicVo
import grails.transaction.Transactional

@Transactional
class TopicService {

    List<TopicVo> search(TopicSearchCO topicSearchCO) {
        List<TopicVo> createdTopicsList = []

        if (topicSearchCO.id) {

            List<Topic> topicList = Topic.createCriteria().list(max: topicSearchCO.max) {
                eq('createdBy.id', topicSearchCO.id)

                if(topicSearchCO.visibility)
                    eq('visibility', topicSearchCO.visibility)
            }

            topicList.each {
                topic -> createdTopicsList.add(new TopicVo(id: topic.id, name: topic.name, visibility: topic.visibility, createdBy: topic.createdBy))
            }

        }

        return createdTopicsList
    }
}
