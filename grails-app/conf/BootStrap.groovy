import com.ttnd.linksharing.DocumentResource
import com.ttnd.linksharing.LinkResource
import com.ttnd.linksharing.ReadingItem
import com.ttnd.linksharing.Resource
import com.ttnd.linksharing.ResourceRating
import com.ttnd.linksharing.Role
import com.ttnd.linksharing.Subscription
import com.ttnd.linksharing.Topic
import com.ttnd.linksharing.User
import com.ttnd.linksharing.UserRole
import com.ttnd.linksharing.constants.Constants
import enums.Seriousness
import enums.Visibility

//@Log4j
class BootStrap {

    def grailsApplication

    def init = { servletContext ->

        List<User> users = createUsersAndRoles()
        List<Topic> topics = createTopics()
        List<Resource> resources = createResources()
        List<Subscription> subscriptions = subscribeTopics()
        List<ReadingItem> readingItems = createReadingItems()
        List<ResourceRating> resourceRatings = createResourceRatings()

    }


    List<User> createUsersAndRoles() {
        List<User> users = []

        User normalUser = new User(username: 'normal', emailID: 'normal@mail.com', password: Constants.DEFAULT_PASSWORD, firstName: 'normal', lastName: 'user', isAdmin: false, isActive: true)
        User adminUser = new User(username: 'admin', emailID: 'admin@mail.com', password: Constants.DEFAULT_PASSWORD, firstName: 'admin', lastName: 'user', isAdmin: true, isActive: true)
        User firstUser = new User('username': 'first', emailID: 'first@mail.com', password: Constants.DEFAULT_PASSWORD, confirmPassword: Constants.DEFAULT_PASSWORD, firstName: 'first', lastName: 'user', isAdmin: false, isActive: true)
        User secondUser = new User('username': 'second', emailID: 'second@mail.com', password: Constants.DEFAULT_PASSWORD, confirmPassword: Constants.DEFAULT_PASSWORD, firstName: 'second', lastName: 'user', isAdmin: false, isActive: true)
        User thirdUser = new User('username': 'third', emailID: 'third@mail.com', password: Constants.DEFAULT_PASSWORD, confirmPassword: Constants.DEFAULT_PASSWORD, firstName: 'third', lastName: 'user', isAdmin: false, isActive: true)
        User fourthUser = new User('username': 'fourth', emailID: 'fourth@mail.com', password: Constants.DEFAULT_PASSWORD, confirmPassword: Constants.DEFAULT_PASSWORD, firstName: 'fourth', lastName: 'user', isAdmin: false, isActive: true)
        User fifthUser = new User('username': 'fifth', emailID: 'fifth@mail.com', password: Constants.DEFAULT_PASSWORD, confirmPassword: Constants.DEFAULT_PASSWORD, firstName: 'fifth', lastName: 'user', isAdmin: false, isActive: true)

        Role normalRole = Role.findOrSaveWhere(authority: 'ROLE_NORMAL')
        Role adminRole = Role.findOrSaveWhere(authority: 'ROLE_ADMIN')

        /*def list = [normalUser, adminUser]*/

        if (User.count() == 0) {
            log.info "Initially, no users exist in the table"

            /*list.each {
                user ->
                    if (user.saveInstance()) {
                        users.add(user)
                    }
            }*/

            if (normalUser.saveInstance()) {
                users.add(normalUser)
                UserRole.create(normalUser, normalRole, true)
            }

            if (adminUser.saveInstance()) {
                users.add(adminUser)
                UserRole.create(adminUser, adminRole, true)
                UserRole.create(adminUser, normalRole, true)
            }
        } else
            log.info "There are some records present in the table. Hence, could not perform desired operations."

        return users
    }

    List<Topic> createTopics() {
        List<User> users = User.list()
        List<Topic> topics = []

        users.each
                {
                    user ->
                        if (!user.topics?.size()) {
                            (1..5).each
                                    {
                                        Topic topic = new Topic(name: "Topic $it", visibility: Visibility.PUBLIC, createdBy: user)

                                        if (topic.saveInstance()) {
                                            user.addToTopics(topic)
                                            topics.add(topic)
                                        }
                                    }
                        }
                }

        return topics

    }

    List<Resource> createResources() {
        List<Topic> topics = Topic.list()
        List<Resource> resources = []

        topics.each
                {
                    topic ->
                        if (!topic.resources?.size()) {
                            2.times
                                    {
                                        Resource documentResource = new DocumentResource(description: "${topic.name}Doc${it}", topic: topic, createdBy: topic.createdBy, filePath: "some/file/path")
                                        Resource linkResource = new LinkResource(description: "${topic.name}Link${it}", topic: topic, createdBy: topic.createdBy, url: "http://www.someurl.com")

                                        if (documentResource.saveInstance()) {

                                            resources.add(documentResource)
                                            topic.addToResources(documentResource)
                                        }

                                        if (linkResource.saveInstance()) {

                                            resources.add(linkResource)
                                            topic.addToResources(linkResource)
                                        }
                                    }
                        }

                }
        return resources
    }

    void subscribeTopics() {
        List<User> users = User.list()
        List<Topic> topics = Topic.list()
        List<Subscription> subscriptions = Subscription.list()

        users.each {
            user ->
                topics.each
                        {
                            topic ->
                                if (!Subscription.countByUserAndTopic(user, topic)) {
                                    Subscription subscription = new Subscription(user: user, topic: topic, seriousness: Seriousness.VERY_SERIOUS)

                                    if (subscription.saveInstance()) {
                                        user.addToSubscriptions(subscription)
                                    }
                                }
                        }
        }
    }


    List<ReadingItem> createReadingItems() {
        List<User> users = User.list()
        List<ReadingItem> readingItems = []

        Subscription.list().each {
            Subscription subscription ->
                User user = subscription.user
                Topic topic = subscription.topic
                if (topic.createdBy != user) {
                    Resource.findAllByTopic(topic).each {
                        Resource resource ->
                            if (!ReadingItem.countByUserAndResource(user, resource)) {
                                ReadingItem readingItem = new ReadingItem(user: user, resource: resource, isRead: false)
                                if (readingItem.saveInstance()) {
                                    readingItems.add(readingItem)
                                    user.addToReadingItems(readingItem)
                                }
                            }
                    }
                }

        }

        return readingItems
    }

    List<ResourceRating> createResourceRatings() {
        List<ResourceRating> resourceRatings = []
        List<User> users = User.list()
        List<ReadingItem> readingItems = ReadingItem.list()


        readingItems.each
                {
                    readingItem ->
                        if (!readingItem.isRead) {
                            ResourceRating resourceRating = new ResourceRating(user: readingItem.user, resource: readingItem.resource, score: 3)

                            if (resourceRating.saveInstance()) {

                                resourceRatings.add(resourceRating)
                                readingItem.user.addToResourceRatings(resourceRating)
                            }
                        }
                }
        return resourceRatings
    }

    def destroy = {
    }


}
