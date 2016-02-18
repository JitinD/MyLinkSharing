import com.ttnd.linksharing.DocumentResource
import com.ttnd.linksharing.LinkResource
import com.ttnd.linksharing.Resource
import com.ttnd.linksharing.Subscription
import com.ttnd.linksharing.Topic
import com.ttnd.linksharing.User
import com.ttnd.linksharing.constants.Constants
import enums.Seriousness
import enums.Visibility

class BootStrap {

    def grailsApplication

    def init = { servletContext -> //println(grailsApplication.config.grails.sampleVariable)

        List<User> users = createUsers()
        List<Topic> topics = createTopics()
        //createResources()
        //subscribeTopics()
        createReadingItems()

    }
    def destroy = {
    }

    List<User> createUsers() {
        List<User> users = []

        User normalUser = new User('userName': 'normal', emailID: 'normal@mail.com', password: Constants.DEFAULT_PASSWORD, firstName: 'normal', lastName: 'user', isAdmin: false)
        User adminUser = new User('userName': 'admin', emailID: 'admin@mail.com', password: Constants.DEFAULT_PASSWORD, firstName: 'admin', lastName: 'user', isAdmin: true)

        if (User.count() == 0)
        {
            log.info "Initially, no users exist in the table"

            if (normalUser.save(failOnError: true, flush: true) && adminUser.save(failOnError: true, flush: true))
            {
                users.add(normalUser)
                users.add(adminUser)

                log.info "${normalUser} and ${adminUser} saved successfully"
            }
            else
                log.error "Error saving ${normalUser.errors.allErrors} and ${adminUser.errors.allErrors}"
        }
        else
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
                                        Topic topic = new Topic(name: "Topic" + it, visibility: Visibility.PUBLIC, createdBy: user)
                                        user.addToTopics(topic)
                                        topics.add(topic)

                                        if (topic.save())
                                            log.info "Topic ${topic} saved successfully"
                                        else
                                            log.error "Error saving ${topic.errors.allErrors}"
                                    }

                            if (user.save())
                                log.info "User ${user} saved successfully"
                            else
                                log.error "Error saving ${user.errors.allErrors}"
                        }
                }

        return topics

    }

    //Not working
    void createResources() {
        List<Topic> topics = Topic.list()
        //List<Resource> resources = []

        topics.each
                {
                    topic ->
                        if (!topic.resources?.count()) {
                            2.times
                                    {
                                        DocumentResource documentResource = new DocumentResource(description: topic.name + "Doc" + it, createdBy: topic.createdBy, filePath: "some/file/path")
                                        LinkResource linkResource = new LinkResource(description: topic.name + "Doc" + it, createdBy: topic.createdBy, url: "http://www.someurl.com")

                                        if (documentResource.save() && linkResource.save())
                                            log.info "${documentResource} and ${linkResource} saved successfully"
                                        else
                                            log.error "Error saving ${documentResource.errors.allErrors} and ${linkResource.errors.allErrors}"
                                    }

                            if (topic.save())
                                log.info "Topic ${topic} saved successfully"
                            else
                                log.error "Error saving ${topic.errors.allErrors}"
                        }

                }
    }

    void subscribeTopics() {
        List<User> users = User.list()
        List<Topic> topics = Topic.list()
        List<Subscription> subscriptions = Subscription.list()

        users.each {
                        user -> topics.each
                                {
                                    topic ->    if(topic.createdBy != user)
                                                {
                                                    Subscription subscription = new Subscription(user: user, topic: topic, seriousness: Seriousness.VERY_SERIOUS)

                                                    if(subscription.save())
                                                        log.info "${subscription} saved successfully"
                                                    else
                                                        log.error "Error saving ${subscription.errors.allErrors}"
                                                }
                                }
                    }
    }


    void createReadingItems()
    {
        List<User> users = User.list()

    }
}
