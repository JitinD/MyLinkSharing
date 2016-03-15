package com.ttnd.linksharing

class LinkResourceController extends ResourceController {

    def save(LinkResource linkResource) {

        linkResource.createdBy = session.user

        if (linkResource.saveInstance()) {
            addToReadingItems(linkResource)
            flash.message = g.message(code: "is.saved.link")
        } else {
            flash.error = g.message(code: "not.saved.link")

        }

        if(request.forwardURI == request.contextPath)
            redirect(controller: "login", action: "index")
        else
            redirect(uri: [request.forwardURI - request.contextPath])
    }
}
