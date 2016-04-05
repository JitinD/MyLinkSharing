package com.ttnd.linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(ResourceRating)
class ResourceRatingSpec extends Specification {

    @Unroll
    def "validating score on a resource by a user : #sno"() {
        setup:
        ResourceRating resourceRating = new ResourceRating(resource: resource, user: user, score: score)

        when:
        Boolean isValid = resourceRating.validate()

        then:
        isValid == result

        where:
        sno | resource               | user       | score | result
        1   | null                   | new User() | 5     | false
        2   | new DocumentResource() | null       | 5     | false
        3   | new DocumentResource() | new User() | null  | false
        4   | new DocumentResource() | new User() | 5     | true
        5   | new DocumentResource() | new User() | 0     | false
        6   | new DocumentResource() | new User() | 6     | false
    }

    def "validating a resource is only rated once by a user"()
    {
        setup:
        User user = new User(username: "testUser", firstName: "test", lastName: "User", emailID: "testUser@mail.com", password: "testPassword")
        Resource resource = new DocumentResource(description: "testDocResource", createdBy: user, filePath: "test/doc")

        ResourceRating resourceRating = new ResourceRating(resource: resource, user: user, score: 3)
        ResourceRating newResourceRating = new ResourceRating(resource: resource, user: user, score: 3)

        when:
        resourceRating.save(flush: true)
        newResourceRating.save(flush: true)

        then:
        resourceRating.errors.getAllErrors().size() == 0
        newResourceRating.errors.getAllErrors().size() == 1
        newResourceRating.errors.getFieldError('resource')

    }

}
