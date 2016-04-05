<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta name="layout" content="main">
        <title>User Title bar</title>
    </head>

    <body>
        <div class="row">
            <div class="col-xs-4">

                <div class="row panel panel-default panel-body">
                    <g:render template = "/user/show" model = "[user : user]"/>
                    <g:hiddenField name="id" id = "id" value = "${user.userId}"/>
                </div>

                <div class = "row">
                    <div class="panel panel-default">
                        <div class="panel-heading panelHeaders">
                            <span class = "panelHeadersText">Topics</span>
                        </div>

                        <div id = "createdTopics">

                        </div>
                    </div>
                </div>

                <div class = "row">
                    <div class="panel panel-default">
                        <div class="panel-heading panelHeaders">
                            <span class = "panelHeadersText">Subscriptions</span>
                        </div>

                        <div id = "subscribedTopics">

                        </div>
                    </div>
                </div>
            </div>

            <div class="col-md-7 col-md-push-1">
                <div class="row">
                    <div class="panel panel-default">
                        <div class="panel-heading panelHeaders">
                            <span class = "panelHeadersText">Posts</span>
                        </div>

                        <div class="panel-body">
                            <g:each in = "${createdPosts}" var = "post">
                                <g:render template = "/resource/show" model = "[post: post]" />
                            </g:each>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <g:javascript>
            $(document).ready(function(){
                $.ajax({
                    url: "/user/topics",
                    data:{id: $("#id").val()},
                    success: function(result){
                        $("#createdTopics").html(result)
                    }

                });

                $.ajax({
                    url: "/user/subscriptions",
                    data:{id: $("#id").val()},
                    success: function(result){
                        $("#subscribedTopics").html(result)
                    }

                });
            });
        </g:javascript>
    </body>

</html>