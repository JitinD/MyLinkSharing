package com.ttnd.linksharing

import CO.ResourceSearchCO

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

    def search(ResourceSearchCO resourceSearchCO)
    {
        List<Resource> resources = Resource.search(resourceSearchCO).list()
        render "${resources}"
    }
}
