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
                    </div>

                    <div class="row">
                        <div class="panel panel-default">
                            <div class="panel-heading panelHeaders">
                                <span class = "panelHeadersText">Subscriptions</span>
                                <div class="pull-right">
                                    <a href="#">View All</a>
                                </div>
                            </div>

                            <g:each in="${subscribedTopics}" var="topic">
                                <g:render template="/topic/show" model="[topic: topic]"/>
                            </g:each>

                        </div>
                    </div>

                    <div class="row">
                        <div class="panel panel-default">

                            <div class="panel-heading panelHeaders">
                                <span class = "panelHeadersText">Trending topics</span>
                            </div>

                            <g:each in="${trendingTopics}" var="topic">
                                <g:render template="/topic/show" model="[topic: topic]"/>
                            </g:each>
                        </div>
                    </div>
                </div>

                <div class="col-xs-7 col-xs-push-1">
                    <div class="row">
                        <div class="panel panel-default">
                            <div class="panel-heading panelHeaders">
                                <span class = "panelHeadersText">Inbox</span>
                            </div>

                            <div class="panel-body">
                                <g:each in = "${inboxPosts}" var = "post">
                                    <g:render template = "/resource/show" model = "[post: post]" />
                                </g:each>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    </body>
</html>