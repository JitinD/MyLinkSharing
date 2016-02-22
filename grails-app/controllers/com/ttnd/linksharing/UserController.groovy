package com.ttnd.linksharing

class UserController {
    //String domainName = "com.ttnd.linksharing.User"
    //String userName = "normal"
    //String password = "password"
    //Map domainParams = [userName:"normal", password:"defaultPassword"]

    def index()
    {
        render "Dashboard of user : ${session.user.userName}"
        //User user = User.findByUserNameAndPassword(domainParams.userName, domainParams.password)
        //User user = "${domainName}.findBy${domainParams[0].key}And${domainParams[1].key}(${domainParams[0].value}, ${domainParams[1].value})"

        //def domain = grailsApplication.getArtefact("Domain", domainName)?.getClazz()?.get(1)
        //Map domainParameters = domain.properties

        //def domainInstance = grailsApplication.getDomainClass(domainName).clazz
        //def parameters = domainInstance.declaredFields.collect{it.name}
        //User user = ((User)${domainInstance}).findByUserNameAndPassword(domainParams.userName, domainParams.password)
        //render "${user}"

        //String className = "User"
        //Class clazz = grailsApplication.domainClasses.find { it.clazz.simpleName == className }.clazz

        //render "${userName}"
    }
}
