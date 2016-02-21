import com.ttnd.linksharing.DocumentResource
import com.ttnd.linksharing.LinkResource
import com.ttnd.linksharing.ReadingItem
import com.ttnd.linksharing.Resource
import com.ttnd.linksharing.ResourceRating
import com.ttnd.linksharing.Subscription
import com.ttnd.linksharing.Topic
import com.ttnd.linksharing.User
import com.ttnd.linksharing.constants.Constants
import enums.Seriousness
import enums.Visibility
import groovy.util.logging.Log4j

//@Log4j
class BootStrap {

    def grailsApplication

    def init = { servletContext -> //println(grailsApplication.config.grails.sampleVariable)

        List<User> users = createUsers()
        List<Topic> topics = createTopics()
        List<Resource> resources = createResources()
        List<Subscription> subscriptions = subscribeTopics()
        List<ReadingItem> readingItems = createReadingItems()
        List<ResourceRating> resourceRatings = createResourceRatings()

    }

    List<User> createUsers() {
        List<User> users = []

        User normalUser = new User('userName': 'normal', emailID: 'normal@mail.com', password: Constants.DEFAULT_PASSWORD, firstName: 'normal', lastName: 'user', isAdmin: false, isActive: true)
        User adminUser = new User('userName': 'admin', emailID: 'admin@mail.com', password: Constants.DEFAULT_PASSWORD, firstName: 'admin', lastName: 'user', isAdmin: true, isActive: true)

        if (User.count() == 0) {
            log.info "Initially, no users exist in the table"

            if (User.save(normalUser) && User.save(adminUser)) {

                users.add(normalUser)
                users.add(adminUser)

                log.info "${normalUser} and ${adminUser} saved successfully"
            } else
                log.error "Error saving ${normalUser.errors.allErrors} and ${adminUser.errors.allErrors}"
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
                        if (!user.topics?.count()) {
                            (1..5).each
                                    {
                                        Topic topic = new Topic(name: "Topic $it", visibility: Visibility.PUBLIC, createdBy: user)

                                        if (Topic.save(topic)) {
                                            user.addToTopics(topic)
                                            topics.add(topic)
                                            log.info "Topic ${topic} saved successfully"
                                        } else
                                            log.error "Error saving ${topic.errors.allErrors}"
                                    }

                            /*if (user.save())
                                log.info "User ${user} saved successfully"
                            else
                                log.error "Error saving ${user.errors.allErrors}"*/
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
                        if (!topic.resources?.count()) {
                            2.times
                                    {
                                        Resource documentResource = new DocumentResource(description: "${topic.name}Doc${it}", topic: topic, createdBy: topic.createdBy, filePath: "some/file/path")
                                        Resource linkResource = new LinkResource(description: "${topic.name}Link${it}", topic: topic, createdBy: topic.createdBy, url: "http://www.someurl.com")

                                        if (Resource.save(documentResource) && Resource.save(linkResource)) {

                                            resources.add(documentResource)
                                            resources.add(linkResource)

                                            topic.addToResources(documentResource)
                                            topic.addToResources(linkResource)
                                            log.info "${documentResource} and ${linkResource} saved successfully"
                                        } else
                                            log.error "Error saving ${documentResource.errors.allErrors} and ${linkResource.errors.allErrors}"
                                    }

                            /*if (topic.save())
                                log.info "Topic ${topic} saved successfully"
                            else
                                log.error "Error saving ${topic.errors.allErrors}"*/
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
                                if (!Subscription.findByUserAndTopic(user, topic)) {
                                    Subscription subscription = new Subscription(user: user, topic: topic, seriousness: Seriousness.VERY_SERIOUS)

                                    if (Subscription.save(subscription)) {
                                        user.addToSubscriptions(subscription)
                                        log.info "${subscription} saved successfully"
                                    } else
                                        log.error "Error saving ${subscription.errors.allErrors}"
                                }
                        }
        }
    }


    List<ReadingItem> createReadingItems() {
        List<User> users = User.list()
        List<Topic> topics = Topic.list()
        List<ReadingItem> readingItems = []

        users.each
                {
                    user ->
                        topics.each
                                {
                                    topic ->
                                        if (Subscription.findByUserAndTopic(user, topic)) {

                                            topic.resources.each
                                                    {
                                                        resource ->
                                                            if (resource.createdBy != user && !user.readingItems?.contains(resource)) {
                                                                ReadingItem readingItem = new ReadingItem(user: user, resource: resource, isRead: false)

                                                                if (ReadingItem.save(readingItem)) {
                                                                    readingItems.add(readingItem)
                                                                    user.addToReadingItems(readingItem)
                                                                    log.info "${readingItem} saved successfully"
                                                                }
                                                                else
                                                                    log.error "Error saving ${readingItem.errors.allErrors}"
                                                            }

                                                    }

                                        }
                                }

                        //user.save()

                }

        return readingItems
    }

    List<ResourceRating> createResourceRatings() {
        List<ResourceRating> resourceRatings = []
        List<User> users = User.list()

        users.each
                {
                    user ->
                        user.readingItems?.each
                                {
                                    readingItem ->
                                        if (!readingItem.isRead) {
                                            ResourceRating resourceRating = new ResourceRating(user: readingItem.user, resource: readingItem.resource, score: 3)

                                            if (ResourceRating.save(resourceRating)) {

                                                resourceRatings.add(resourceRating)
                                                user.addToResourceRatings(resourceRating)
                                                log.info "${resourceRating} saved successfully"
                                            }
                                            else
                                                log.error "Error saving ${resourceRating.errors.allErrors}"
                                        }
                                }
                }

        return resourceRatings
    }

    def destroy = {
    }




}
