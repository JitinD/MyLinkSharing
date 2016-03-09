package com.ttnd.linksharing

import grails.transaction.Transactional

class DocumentResourceController extends ResourceController {

    def index() {}

    @Transactional
    def save(DocumentResource documentResource) {

        documentResource.createdBy = session.user

        def file = params.file

        if (file.empty) {
            flash.error = "Invalid file"
        } else {
            String filePath = "${grailsApplication.config.grails.documentsPath}/${UUID.randomUUID()}.pdf"
            documentResource.contentType = file.contentType
            documentResource.filePath = filePath

            if (documentResource.saveInstance()) {
                File savedFile = new File(filePath)
                file.transferTo(savedFile)
                addToReadingItems(documentResource)

                flash.message = "File saved successfully"
            } else
                flash.error = "File couldn't be saved"
        }

        redirect(controller: "login", action: "index")
    }

    def download(Long id)
    {
        User user = session.user
        DocumentResource documentResource = (DocumentResource)Resource.get(id)

        if(documentResource && documentResource.canViewBy(user)){
            def file = new File(documentResource.filePath)

            if(file.exists()){
                response.setContentType(Constants.AppConstants.DOCUMENT_CONTENT_TYPE)
                response.setHeader("Content-disposition", "attachment;filename=${documentResource.fileName}")
                response.outputStream << file.bytes
            }
            flash.error = "Desired resource doesn't exist."
        }
        else{
            flash.error = "Cannot access the desired resource. Permission denied."
        }

        redirect(controller: "resource", action: "show", params: [id: id])
    }
}
