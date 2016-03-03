package com.ttnd.linksharing

class SampleTagLib {
    static defaultEncodeAs = [taglib: 'html']
    static namespace = "ls"
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def each = {
        attribute, body ->
            Boolean isAdmin = Boolean.valueOf(attribute.isAdmin)

            if (isAdmin)
                out << body()
    }

    def showList = {

        List list = [1,2,4,5,6]
        out << render(template : '/login/template', model : [Alist : list])
    }
}
