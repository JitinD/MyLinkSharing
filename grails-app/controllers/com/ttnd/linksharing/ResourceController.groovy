package com.ttnd.linksharing

class ResourceController {

    def index() { }

    def delete(Integer id)
    {
        Resource resource = Resource.load(id)

        if(resource)
        {
            resource.delete()
            render "Resource ${resource} deleted."
        }
        else
        {
            flash.message = "${resource} could not be deleted, ${resource.errors.allErrors}"
            render "${resource.errors.allErrors.collect { message(error: it) }}"
        }

    }
}
