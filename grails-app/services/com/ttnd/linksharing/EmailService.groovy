package com.ttnd.linksharing

import DTO.EmailDTO
import grails.transaction.Transactional

@Transactional
class EmailService {

    def groovyPageRenderer
    def mailService

    void sendMail(EmailDTO emailDTO) {

        def message = null

        if(emailDTO.model){
            if(emailDTO.model.newPassword)
                message = groovyPageRenderer.render(template: "/email/password", model: [newPassword: emailDTO.model.newPassword])
            else
                message = groovyPageRenderer.render(template: "/email/invite", model: [topicId: emailDTO.model.id, hostURL: emailDTO.model.hostURL])
        }

        mailService.sendMail {
            to emailDTO.to
            subject emailDTO.subject
            html message ?: emailDTO.content
        }
    }

    void sendUnreadResourcesEmail(User user, List<ReadingItem> unreadReadingItems){
        EmailDTO emailDTO = new EmailDTO()

        emailDTO.to = user.emailID
        emailDTO.subject = "You have unread posts"
        emailDTO.content = groovyPageRenderer.render(template: "/email/unreadItems", model: [user: user, unreadResources: unreadReadingItems])


        sendMail(emailDTO)
    }
}
