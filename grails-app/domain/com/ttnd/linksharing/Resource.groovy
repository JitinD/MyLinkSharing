package com.ttnd.linksharing

import CO.ResourceSearchCO
import VO.RatingInfoVo
import VO.TopicVo
import enums.Visibility

abstract class Resource {

    String description;
    User createdBy;
    Date dateCreated;
    Date lastUpdated;

    static constraints = {
        description(nullable: false, blank: false)
        createdBy(nullable: false, blank: false)
        topic(nullable: false, blank: false)
    }

    static mapping = {
        description type: 'text'
    }

    static belongsTo = [topic: Topic]
    static hasMany = [ratings: ResourceRating, readItems: ReadingItem]

    static transients = ['ratingInfo']

    static namedQueries = {
        search {
            ResourceSearchCO resourceSearchCO ->
                if (resourceSearchCO.topicId) {
                    eq('topic.id', resourceSearchCO.topicId)
                }

                if (resourceSearchCO.visibility) {
                    'topic' {
                        eq('visibility', resourceSearchCO.visibility)
                    }
                }
        }
    }

    public Resource saveInstance() {

        Resource resource = this

        resource.validate()

        if (resource.hasErrors()) {
            log.error("Resource has validation errors : ${resource.errors}")

            return null
        } else {
            resource.save(failOnError: true, flush: true)
            log.info "${resource} saved successfully"

            return resource
        }
    }

    def getRatingInfo() {
        List<Integer> result = ResourceRating.createCriteria().get {
            projections {
                count('id', 'totalVotes')
                sum('score', 'totalScore')
                avg('score', 'avgScore')
            }
            eq('resource', this)
            order('totalVotes', 'desc')
        }

        new RatingInfoVo(totalVotes: result[0], totalScore: result[1], averageScore: result[2])
    }

    public static List<Resource> getTopPosts() {

        List<Resource> resources = []
        List<Resource> topPosts = []

        def result = ResourceRating.createCriteria().list(max: 5) {
            projections {
                property('resource.id')
            }

            groupProperty('resource.id')
            count('id', 'totalVotes')
            order('totalVotes', 'desc')
        }

        List list = result.collect { it[0] }
        resources = Resource.getAll(list)

        resources.each {
            resource ->
                if (resource) {
                    if (resource.topic.visibility == Visibility.PUBLIC)
                        topPosts.add(resource)
                }
        }

        return topPosts
    }


    public static List<Resource> getRecentPosts() {

        List<Resource> recentPosts = createCriteria().list(max: 5) {

            order('lastUpdated', 'desc')
            createAlias('topic', 't')
            eq('t.visibility', Visibility.PUBLIC)

        }

        return recentPosts
    }

}