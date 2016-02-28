<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta name="layout" content="main">
        <title>Topic title bar</title>
    </head>

    <body>

        <div class="container">

            <div class="row">
                <div class="col-xs-5">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Topic: Grails
                        </div>

                        <div class="panel-body">
                            <div class="row">
                                <div class="col-xs-3">
                                    <img src="" class="img img-thumbnail img-responsive" style="width:80px; height:70px;"/>
                                </div>

                                <div class="col-xs-9">
                                    <div class="row">
                                        <div class="col-xs-7">${topic.name}</div>
                                    </div>

                                    <div class="row">
                                        <div class="col-xs-4 text-muted"><small>@${topic.createdBy.userName}</small></div>

                                        <div class="col-xs-4 text-muted">Subscriptions</div>

                                        <div class="col-xs-4 text-muted">Post</div>
                                    </div>

                                    <div class="row">
                                        <div class="col-xs-4"><a href="#"><ins>Subscribe</ins></a></div>

                                        <div class="col-xs-4">50</div>

                                        <div class="col-xs-4">30</div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="panel panel-footer">
                            <div class="row">
                                <div class="col-xs-4">
                                    <div class="dropdown">
                                        <button class="btn btn-default btn-sm dropdown-toggle" type="button"
                                                data-toggle="dropdown">Serious
                                            <span class="caret"></span>
                                        </button>

                                        <ul class="dropdown-menu">
                                            <li><a href="#">Serious</a></li>
                                            <li><a href="#">Casual</a></li>
                                        </ul>
                                    </div>
                                </div>

                                <div class="col-xs-4">
                                    <div class="dropdown">
                                        <button class="btn btn-default btn-sm dropdown-toggle" type="button"
                                                data-toggle="dropdown">Public
                                            <span class="caret"></span>
                                        </button>

                                        <ul class="dropdown-menu">
                                            <li><a href="#">Public</a></li>
                                            <li><a href="#">Private</a></li>
                                        </ul>
                                    </div>

                                </div>

                                <div class="col-xs-4">
                                    <span><a href="#"><ins>Edit</ins></a></span>&nbsp;
                                    <span><a href="#"><ins>Delete</ins></a></span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Users: Grails
                        </div>

                        <g:render template="/user/show"/>

                    </div>

                </div>

                <div class="col-xs-7">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Posts: ${topic.name}
                        </div>

                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-3">
                                    <img src="" class="img img-thumbnail img-responsive" style="width:150px;height:150px">
                                </div>

                                <div class="col-md-9 panel">
                                    <span class="text-primary">Uday Pratap Singh</span>
                                    <span class="text-muted">@uday 5 min</span>
                                    <span class="text-primary pull-right">Grails</span>

                                    <div class="panel-body text-justify">
                                        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis
                                    </div>

                                    <div class="panel-footer">
                                        <a href="#"><span class="fa fa-facebook-square" style="font-size:20px"></span></a>&nbsp;
                                        <a href="#"><span class="fa fa-tumblr" style="font-size:20px"></span></a>&nbsp;
                                        <a href="#"><span class="fa fa-google-plus" style="font-size:20px"></span></a>&nbsp;
                                        <a href="#" class="pull-right">Download</a>&nbsp;
                                        <a href="#" class="pull-right">View Full Site</a>&nbsp;
                                        <a href="#" class="pull-right">Mark as Read</a>&nbsp;
                                        <a href="#" class="pull-right">View Post</a>&nbsp;
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-3">
                                    <img src="" class="img img-thumbnail img-responsive" style="width:150px;height:150px">
                                </div>

                                <div class="col-md-9 panel">
                                    <span class="text-primary">Uday Pratap Singh</span>
                                    <span class="text-muted">@uday 10 min</span>
                                    <span class="text-primary pull-right">Grails</span>

                                    <div class="panel-body text-justify">
                                        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis
                                    </div>

                                    <div class="panel-footer">
                                        <a href="#"><span class="fa fa-facebook-square" style="font-size:20px"></span></a>&nbsp;
                                        <a href="#"><span class="fa fa-tumblr" style="font-size:20px"></span></a>&nbsp;
                                        <a href="#"><span class="fa fa-google-plus" style="font-size:20px"></span></a>&nbsp;
                                        <a href="#" class="pull-right">Download</a>&nbsp;
                                        <a href="#" class="pull-right">View Full Site</a>&nbsp;
                                        <a href="#" class="pull-right">Mark as Read</a>&nbsp;
                                        <a href="#" class="pull-right">View Post</a>&nbsp;
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