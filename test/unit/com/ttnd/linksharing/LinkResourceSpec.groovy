package com.ttnd.linksharing

import enums.Visibility
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll


/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(LinkResource)
class LinkResourceSpec extends Specification {

    @Unroll
    def "validating Link resource URL : #sno"() {
        setup:
        LinkResource linkResource = new LinkResource(description: description, createdBy: user, topic: topic, url: url)

        when:
        Boolean isValid = linkResource.validate();

        then:
        isValid == result;

        where:
        sno | description          | user       | topic       | url                               | result
        1   | "Test Link Resource" | new User() | new Topic() | "http://www.testlinkresource.com" | true
        2   | "Test Link Resource" | null       | new Topic() | "http://www.testlinkresource.com" | false
        3   | "Test Link Resource" | new User() | null        | "http://www.testlinkresource.com" | false
        4   | "Test Link Resource" | new User() | new Topic() | ""                                | false //isValid is returning true
        5   | "Test Link Resource" | new User() | new Topic() | null                              | true
        6   | ""                   | new User() | new Topic() | "http://www.testlinkresource.com" | false
        7   | null                 | new User() | new Topic() | "http://www.testlinkresource.com" | false
        7   | "Test Link Resource" | new User() | new Topic() | "a/c/d"                           | false

    }

    def "validating toString of linkResource"() {
        setup:
        LinkResource linkResource = new LinkResource(url: url)

        when:
        linkResource.toString() == result

        then:
        noExceptionThrown()

        where:
        url                               | result
        "http://www.testLinkResource.com" | "http://www.testLinkResource.com"
    }

}
