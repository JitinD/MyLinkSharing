package com.ttnd.linksharing

class DocumentResource extends Resource {

    String filePath;

    static constraints = {
        filePath(blank: false, nullable: true)
    }

    String toString()
    {
        return filePath
    }
}