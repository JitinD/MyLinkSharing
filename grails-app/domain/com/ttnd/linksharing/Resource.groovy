package com.ttnd.linksharing

import CO.ResourceSearchCO
import VO.PostVO
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
                if (resourceSearchCO.topicId && resourceSearchCO.q) {
                    eq('topic.id', resourceSearchCO.topicId)
                    ilike('description',"%${resourceSearchCO.q}%")
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

    public static List<PostVO> getTopPosts() {

        List<PostVO> topPostVOs = []

        List<Resource> topPosts = []
        List topicIds = []

        def result = ResourceRating.createCriteria().list(max: 5) {

            createAlias('resource', 'r')
            createAlias('r.topic', 't')
            groupProperty('r.id')
            avg('score', 'rating')
            order('rating', 'desc')
            eq('t.visibility', Visibility.PUBLIC)

        }

        topicIds = result.collect {
            it[0]
        }

        topPosts = Resource.getAll(topicIds)

        topPosts.each {
            post ->
                topPostVOs.add(new PostVO(userId: post.createdBy.id, topicId: post.topic.id, resourceId: post.id,
                        user: post.createdBy.name, userName: post.createdBy.userName, topicName: post.topic.name,
                        description: post.description, url: post.class.equals(LinkResource) ? post.url : null,
                        filePath: post.class.equals(DocumentResource) ? post.filePath : null, createdDate: post.dateCreated))
        }

        return topPostVOs
    }


    public static List<PostVO> getRecentPosts() {

        List<PostVO> recentPostVOs = []

        List<Resource> recentPosts = createCriteria().list(max: 5) {

            order('lastUpdated', 'desc')
            createAlias('topic', 't')
            eq('t.visibility', Visibility.PUBLIC)

        }

        recentPosts.each {
            post ->
                recentPostVOs.add(new PostVO(userId: post.createdBy.id, topicId: post.topic.id, resourceId: post.id,
                        user: post.createdBy.name, userName: post.createdBy.userName, topicName: post.topic.name,
                        description: post.description, url: post.class.equals(LinkResource) ? post.url : null,
                        filePath: post.class.equals(DocumentResource) ? post.filePath : null, createdDate: post.dateCreated))
        }

        return recentPostVOs
    }


    public static Boolean isLinkResource(Long id) {
        Resource resource = Resource.get(id)

        return resource.class.equals(LinkResource) ? true : false

    }

    public static PostVO getPostInfo(Long id) {

        PostVO postVO = null

        createCriteria().get {
            eq('id', id)
        }.each
                {
                    resourceInfo ->
                        postVO = new PostVO(userId: resourceInfo.createdBy.id, topicId: resourceInfo.topic.id, resourceId: resourceInfo.id, user: resourceInfo.createdBy.name,
                                userName: resourceInfo.createdBy.userName, topicName: resourceInfo.topic.name, description: resourceInfo.description,
                                url: resourceInfo.class.equals(LinkResource) ? resourceInfo.url : null, filePath: resourceInfo.class.equals(DocumentResource) ? resourceInfo.filePath : null,
                                createdDate: resourceInfo.dateCreated)
                }

        return postVO
    }

    public Boolean canViewBy(User user) {
        if (this.topic.canViewedBy(user))
            return true

        return false
    }


    public Boolean deleteFile() {
        log.info "Implemented in sub-classes"
    }

}