<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta name="layout" content="main">
        <title>Topic title bar</title>
    </head>

    <body>



            <div class="row">
                <div class="col-xs-5">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Topic: Grails
                        </div>

                        <g:render template="/topic/show" model="[topic: topic]"/>

                    </div>

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Users: Grails
                        </div>
                        <g:each in = "${subscribedUsers}" var = "user">
                            <g:render template="/user/show" model = "[user : user]" />
                        </g:each>
                    </div>

                </div>

                <div class="col-xs-7">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Posts: ${topic.name}
                        </div>

                        <div class="panel-body">
                            <g:each in = "${topicPosts}" var = "post">
                                <g:render template = "/resource/show" model = "[post : post]" />
                            </g:each>
                        </div>
                    </div>
                </div>
            </div>

    </body>
</html>