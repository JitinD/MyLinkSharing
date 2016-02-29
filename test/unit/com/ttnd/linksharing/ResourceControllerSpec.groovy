package com.ttnd.linksharing

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

import javax.print.Doc

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */

@Mock([Resource, DocumentResource])
@TestFor(ResourceController)
class ResourceControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
    }

    def "testing resource/delete when id is valid"() {

        setup:
        Resource resource = new DocumentResource(id: 100)
        resource.save(validate: false)

        when:
        controller.delete(resource.id)

        then:
        noExceptionThrown()
        response.contentAsString == "Resource deleted successfully."
    }

    def "testing resource/delete when id is invalid"() {

        when:
        controller.delete(100)

        then:
        Exception e = thrown()
        flash.message == "Resource could not be deleted"
        response.contentAsString == e.message
    }
}
