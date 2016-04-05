package com.ttnd.linksharing

import com.ttnd.linksharing.constants.Constants
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['permitAll'])
class DocumentResourceController extends ResourceController {


    @Secured(['ROLE_NORMAL, ROLE_ADMIN'])
    @Transactional
    def save(DocumentResource documentResource) {

        documentResource.createdBy = session.user

        def file = params.file

        if (file.empty) {
            flash.error = "File is empty"
        } else {
            String filePath = "${grailsApplication.config.grails.documentsPath}/${UUID.randomUUID()}.pdf"
            documentResource.contentType = file.contentType
            documentResource.filePath = filePath

            if(documentResource.contentType != Constants.DOCUMENT_CONTENT_TYPE)
                flash.error = g.message(code: "not.valid.file")
            else {
                if (documentResource.saveInstance()) {
                    File savedFile = new File(filePath)
                    file.transferTo(savedFile)
                    addToReadingItems(documentResource)

                    flash.message = g.message(code: "is.saved.file")
                } else
                    flash.error = g.message(code: "not.saved.file")
            }
        }

        redirect(url: request.getHeader('referer'))
    }

    def download(Long id)
    {
        User user = session.user
        DocumentResource documentResource = (DocumentResource)Resource.get(id)

        if(documentResource && documentResource.canViewBy(user)){
            def file = new File(documentResource.filePath)

            if(file.exists()){
                response.setContentType(Constants.DOCUMENT_CONTENT_TYPE)
                response.setHeader("Content-disposition", "attachment;filename=${documentResource.fileName}")
                response.outputStream << file.bytes
            }
            flash.error = g.message(code: "not.found.file")
        }
        else{
            flash.error = g.message(code: "not.accessible.file")
        }

        redirect(controller: "resource", action: "show", params: [id: id])
    }
}
