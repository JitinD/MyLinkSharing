package com.ttnd.linksharing

import enums.Visibility
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(DocumentResource)
class DocumentResourceSpec extends Specification {

    @Unroll
    def "validating Document resource filepath: #sno"() {
        setup:
        DocumentResource documentResource = new DocumentResource(description: description, createdBy: user, topic: topic, filePath: filePath)

        when:
        Boolean isValid = documentResource.validate();

        then:
        isValid == result;

        where:
        sno | description              | user       | topic       | filePath            | result
        1   | "Test Document Resource" | new User() | new Topic() | "test/doc/resource" | true
        2   | "Test Document Resource" | null       | new Topic() | "test/doc/resource" | false
        3   | "Test Document Resource" | new User() | null        | "test/doc/resource" | false
        4   | "Test Document Resource" | new User() | new Topic() | ""                  | false //isValid is returning true
        5   | "Test Document Resource" | new User() | new Topic() | null                | true
        6   | ""                       | new User() | new Topic() | "test/doc/resource" | false
        7   | null                     | new User() | new Topic() | "test/doc/resource" | false

    }

    def "validating toString of documentResource"() {
        setup:
        DocumentResource documentResource = new DocumentResource(filePath: filePath)

        when:
        String resourePath = documentResource.toString()

        then:
        noExceptionThrown()
        resourePath == result

        where:
        filePath                 | result
        "test/document/resource" | "test/document/resource"
    }
}
