package com.ttnd.linksharing

import com.ttnd.linksharing.constants.Constants

class DocumentResource extends Resource {

    String filePath;
    String contentType
    String fileName

    static transients = ['contentType', 'fileName']

    static constraints = {
        filePath(blank: false, nullable: true)
        contentType(bindable: true, validator: {
            value, documentResource ->
                if(!documentResource.id)
                    return value.equals(Constants.DOCUMENT_CONTENT_TYPE)
        })
    }

    String toString() {
        return filePath ?: ""
    }

    public Boolean deleteFile(){

        Boolean isDeleted = new File(this.filePath).delete()

        if(isDeleted){
            this.delete(flush: true)
            return true
        }
        else
            return false
    }

    public String getFileName(){
        def file = new File(this.filePath)

        return file.name
    }
}