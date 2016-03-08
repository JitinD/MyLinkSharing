package com.ttnd.linksharing

class LinkResource extends Resource{

    String url;

    static constraints = {

        url(blank: false, nullable : true, url: true)
    }

    String toString()
    {
        return url?:""
    }

    public Boolean deleteFile(){

            this.delete(flush: true)
            return true
    }
}