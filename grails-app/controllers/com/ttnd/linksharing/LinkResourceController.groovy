package com.ttnd.linksharing

class LinkResourceController extends ResourceController {

    def index() {}

    def save(LinkResource linkResource) {

        linkResource.createdBy = session.user

        if (linkResource.saveInstance()) {
            addToReadingItems(linkResource)
            flash.message = "Link resource successfully added. ~SUCCESS~"
        } else {
            flash.error = linkResource.errors.allErrors.collect { message(error: it) }

        }
        redirect uri: "/"
    }
}
