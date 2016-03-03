<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta name="layout" content="main">
        <title>User Title bar</title>
    </head>

    <body>

            <div class="row">
                <div class="col-md-4">

                    <g:render template = "/user/show" model = "[user : user]"/>

                    <div class="row">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                Subscriptions
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

                            <div class="panel-heading">
                                Trending topics
                            </div>

                            <g:each in="${trendingTopics}" var="topic">
                                <g:render template="/topic/show" model="[topic: topic]"/>
                            </g:each>
                        </div>
                    </div>
                </div>

                <div class="col-md-7 col-md-push-1">
                    <div class="row">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                Inbox
                            </div>

                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-md-3">
                                        <img src="" class="img img-thumbnail img-responsive"
                                             style="width:150px;height:150px">
                                    </div>

                                    <div class="col-md-9 panel">
                                        <span class="text-primary">Uday Pratap Singh</span>
                                        <span class="text-muted">@uday 5 min</span>
                                        <span class="text-primary pull-right">Grails</span>

                                        <div class="panel-body text-justify">
                                            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis
                                        </div>

                                        <div class="panel-footer">
                                            <a href="#"><span class="fa fa-facebook-square" style="font-size:20px"></span>
                                            </a>
                                            <a href="#"><span class="fa fa-tumblr" style="font-size:20px"></span></a>
                                            <a href="#"><span class="fa fa-google-plus" style="font-size:20px"></span></a>
                                            <a href="#" class="pull-right">Download</a>
                                            <a href="#" class="pull-right">View Full Site</a>
                                            <a href="#" class="pull-right">Mark as Read</a>
                                            <a href="#" class="pull-right">View Post</a>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-3">
                                        <img src="" class="img img-thumbnail img-responsive"
                                             style="width:150px;height:150px">
                                    </div>

                                    <div class="col-md-9 panel">
                                        <span class="text-primary">Uday Pratap Singh</span>
                                        <span class="text-muted">@uday 10 min</span>
                                        <span class="text-primary pull-right">Grails</span>

                                        <div class="panel-body text-justify">
                                            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis
                                        </div>

                                        <div class="panel-footer">
                                            <a href="#"><span class="fa fa-facebook-square" style="font-size:20px"></span>
                                            </a>
                                            <a href="#"><span class="fa fa-tumblr" style="font-size:20px"></span></a>
                                            <a href="#"><span class="fa fa-google-plus" style="font-size:20px"></span></a>
                                            <a href="#" class="pull-right">Download</a>
                                            <a href="#" class="pull-right">View Full Site</a>
                                            <a href="#" class="pull-right">Mark as Read</a>
                                            <a href="#" class="pull-right">View Post</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


    </body>
</html>

