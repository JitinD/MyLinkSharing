package com.ttnd.linksharing

class LinkResource extends Resource{

    String url;

    static constraints = {

        url(blank: false, nullable: false, url: true)
    }
}