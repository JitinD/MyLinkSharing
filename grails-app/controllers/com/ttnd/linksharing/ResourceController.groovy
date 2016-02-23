package com.ttnd.linksharing

class ResourceController {

    def index() { }

    def delete(Long id)
    {
        Resource resource = Resource.load(id)

        try {
            resource.delete(flush: true)
            render "${resource} deleted successfully."
        }catch (Exception e)
        {
            flash.message = "Resource could not be deleted"
            render e.message
        }

    }
}
