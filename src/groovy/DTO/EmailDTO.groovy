package DTO

import grails.validation.Validateable

/**
 * Created by jitin on 12/3/16.
 */

@Validateable
class EmailDTO{
    String to
    String subject
    String view
    String content
    Map model
}
