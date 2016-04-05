package com.ttnd.linksharing

import grails.plugin.springsecurity.annotation.Secured

@Secured(['permitAll'])
class LinkResourceController extends ResourceController {

    def save(LinkResource linkResource) {

        linkResource.createdBy = session.user

        if (linkResource.saveInstance()) {
            addToReadingItems(linkResource)
            flash.message = g.message(code: "is.saved.link")
        } else {
            flash.error = g.message(code: "not.saved.link")

        }
        redirect(url: request.getHeader('referer'))
    }
}
