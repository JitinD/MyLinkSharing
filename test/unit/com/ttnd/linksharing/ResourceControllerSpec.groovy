package com.ttnd.linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
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
        Resource resource = Resource.load(id)

        when:
        controller.delete(id)

        then:
        noExceptionThrown()
        response.contentAsString == "${resource} deleted successfully.".toString()

        where:
        id << [1, 2, 3]

    }

    def "testing resource/delete when id is invalid"() {

        setup:
        Resource resource = Resource.load(id)

        when:
        controller.delete(id)

        then:
        Exception e = thrown(Exception)
        flash.message == "Resource could not be deleted"
        response.contentAsString == e.message

        where:
        id << [1, 2, 3]

    }
}
