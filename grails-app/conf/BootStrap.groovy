import com.ttnd.linksharing.DocumentResource
import com.ttnd.linksharing.LinkResource
import com.ttnd.linksharing.Resource
import com.ttnd.linksharing.Topic
import com.ttnd.linksharing.User
import com.ttnd.linksharing.constants.Constants
import enums.Visibility

class BootStrap {

    def grailsApplication

    def init = { servletContext -> //println(grailsApplication.config.grails.sampleVariable)

        List<User> users = createUsers()
        List<Topic> topics = createTopics()
        createResources()
    }
    def destroy = {
    }

    List<User> createUsers()
    {
        List<User> users = []

        User normalUser = new User('userName' : 'normal', emailID: 'normal@mail.com', password: Constants.DEFAULT_PASSWORD, firstName: 'normal', lastName: 'user', isAdmin: false)
        User adminUser = new User('userName' : 'admin', emailID: 'admin@mail.com', password: Constants.DEFAULT_PASSWORD, firstName: 'admin', lastName: 'user', isAdmin: true)

        if(User.count() == 0)
        {
            log.info "No users exist in the table"

            if (normalUser.save(failOnError: true, flush: true) && adminUser.save(failOnError: true, flush: true))
            {
                users.add(normalUser)
                users.add(adminUser)

                log.info "Normal user and Admin user saved successfully"
            }
            else
                log.info "Normal user and Admin user could not be saved"
        }
        else
            log.info "Some users exist in the table. Hence, users could not be saved"

        return users
    }

    List<Topic> createTopics()
    {
        List<User> users = User.list()
        List<Topic> topics = []

        users.each
                {
                    user -> if(!user.topics?.count())
                    {
                        (1..5).each
                                {
                                    Topic topic = new Topic(name: "Topic"+it,visibility: Visibility.PUBLIC, createdBy: user)
                                    user.addToTopics(topic)
                                    topics.add(topic)

                                    if(topic.save())
                                        log.info "Topics saved successfully"
                                    else
                                        log.info "Topics could not be saved"
                                }

                        if(user.save())
                            log.info "User saved successfully"
                        else
                            log.info "User could not be saved"
                    }
                }

        return topics

    }

    //Not working
    void createResources()
    {
        List<Topic> topics = Topic.list()
        //List<Resource> resources = []

        topics.each
                {
                    topic ->    if(!topic.resources?.count())
                    {
                        2.times
                                {
                                    DocumentResource documentResource = new DocumentResource(description: topic.name+"Doc"+it, createdBy: topic.createdBy, filePath: "some/file/path")
                                    LinkResource linkResource = new LinkResource(description: topic.name+"Doc"+it, createdBy: topic.createdBy, url: "http://www.someurl.com")

                                    if(documentResource.save() && linkResource.save())
                                        log.info "Document resource and link resource saved successfully"
                                    else
                                        log.info "Document resource and link resource could not be saved"
                                }

                        if(topic.save())
                            log.info "Topic saved successfully"
                        else
                            log.info "Topic could not be saved"
                    }

                }
    }
}
