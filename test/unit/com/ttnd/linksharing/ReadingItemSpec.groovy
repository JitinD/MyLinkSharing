package com.ttnd.linksharing

import enums.Visibility
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(ReadingItem)
class ReadingItemSpec extends Specification {

    @Unroll
    def "validating isRead on a resource for a user : #sno"() {
        setup:
        ReadingItem readingItem = new ReadingItem(resource: resource, user: user, isRead: isRead)

        when:
        Boolean isValid = readingItem.validate()

        then:
        isValid == result

        where:
        sno | resource               | user       | isRead | result
        1   | new DocumentResource() | new User() | true   | true
        2   | null                   | new User() | true   | false
        3   | new DocumentResource() | null       | true   | false
        4   | new DocumentResource() | new User() | null   | false

    }

    def "validating reading item status is unique per user"()
    {
        setup:
        User user = new User(username: "testUser", firstName: "test", lastName: "User", emailID: "testUser@mail.com", password: "testPassword")
        Resource resource = new DocumentResource(description: "testDocResource", createdBy: user, filePath: "test/doc")

        ReadingItem readingItem = new ReadingItem(resource: resource, user: user, isRead: true)
        ReadingItem newReadingItem = new ReadingItem(resource: resource, user: user, isRead: false)

        when:
        readingItem.save(flush: true)
        newReadingItem.save(flush: true)

        then:
        readingItem.errors.getAllErrors().size() == 0
        newReadingItem.errors.getAllErrors().size() == 1
        newReadingItem.errors.getFieldError('resource')
    }
}
