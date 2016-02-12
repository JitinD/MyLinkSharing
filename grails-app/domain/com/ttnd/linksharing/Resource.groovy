package com.ttnd.linksharing

abstract class Resource {

    String description;
    User createdBy;
    Topic topic;
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

    static  belongsTo = [topic: Topic]
    static hasMany = [ratings: ResourceRating, readItems: ReadingItem]

}