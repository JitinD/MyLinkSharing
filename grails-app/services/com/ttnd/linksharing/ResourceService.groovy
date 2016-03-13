package com.ttnd.linksharing

import CO.ResourceSearchCO
import VO.PostVO
import grails.transaction.Transactional

@Transactional
class ResourceService {

    List<PostVO> search(ResourceSearchCO resourceSearchCO){

        List<PostVO> posts = []

        List<Resource> resources = Resource.search(resourceSearchCO).list()

        posts = resources?.collect{ Resource.getPostInfo(it.id) }

        return posts
    }
}
