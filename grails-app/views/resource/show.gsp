<%@ page contentType="text/html;charset=UTF-8" %>
<html>

    <head>
        <meta name="layout" content="main">
        <title>Resource Title bar</title>
    </head>

    <body>
        <div class = "row">
            <div class = "col-xs-7">
                <div class = "panel panel-default">
                    <div class = "panel-body">
                        <div class = "row">
                            <div class = "col-xs-2">
                                <img src = "" class = "img img-thumbnail img-responsive" style = "width:80px; height:70px;"/>
                            </div>

                            <div class = "col-xs-10">
                                <div class = "row">
                                    <div class = "col-xs-10">${post.user}</div>
                                    <div class = "col-xs-2"><a href = "/topic/show?id=${post.topicId}"><ins>${post.topicName}</ins></a></div>
                                </div>
                                <div class = "row">
                                    <div class = "col-xs-8 text-muted"><small>(@${post.userName})</small></div>
                                    <div class = "col-xs-4">${post.createdDate} </div>
                                </div>
                                <div class = "row">
                                    <div class = "col-xs-8"></div>
                                    <div class = "col-xs-4">Ratings</div>
                                </div>
                            </div>
                        </div>

                        <div class = "row">
                            <div class = "col-xs-12">
                                ${post.description}
                            </div>
                        </div>
                    </div>

                    <div class = "panel-footer">
                        <div class = "row">

                            <div class = "col-xs-2">
                                <div class = "row">
                                    <div class = "col-xs-1">
                                        <a href="#"><span class="fa fa-facebook-official"></span></a>
                                    </div>

                                    <div class = "col-xs-1">
                                        <a href="#"><span class="fa fa-twitter-square"></span></a>
                                    </div>

                                    <div class = "col-xs-1">
                                        <a href="#"><span class="fa fa-google-plus"></span></a>
                                    </div>
                                </div>
                            </div>

                            <div class = "col-xs-10">
                                <div class = "row">
                                    <div class = "col-xs-offset-6 col-xs-2">
                                        <ls:canDeleteResoure id = "${post.resourceId}"/>
                                    </div>
                                    <div class = "col-xs-2">
                                        <a href="#"><ins>Edit</ins></a>&nbsp;
                                    </div>
                                    <div class = "col-xs-2">
                                        <ls:showResource id = "${post.resourceId}" url = "${post.url}" filePath = "${post.filePath}" />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class = "col-xs-5">
                <div class = "panel panel-default">
                    <div class = "panel-heading">
                        Trending topics
                    </div>
                    <div class = "panel-body">
                        <div class = "row">
                            <div class = "col-xs-3">
                                <img src = "" class = "img img-thumbnail img-responsive" style = "width:80px; height:70px;"/>
                            </div>

                            <div class = "col-xs-9">
                                <div class = "row">
                                    <div class = "col-xs-9">Jitin Dominic</div>
                                    <div class = "col-xs-3"><a href = "#"><ins>Grails</ins></a></div>
                                </div>
                                <div class = "row">
                                    <div class = "col-xs-4 text-muted"><small>@jitin</small></div>
                                    <div class = "col-xs-4 text-muted">Subscriptions</div>
                                    <div class = "col-xs-4 text-muted">Post</div>
                                </div>
                                <div class = "row">
                                    <div class = "col-xs-4"><a href = "#"><ins>Subscribe</ins></a></div>
                                    <div class = "col-xs-4">50</div>
                                    <div class = "col-xs-4">30</div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class = "panel-body">
                        <div class = "row">
                            <div class = "col-xs-3">
                                <img src = "" class = "img img-thumbnail img-responsive" style = "width:80px; height:70px;"/>
                            </div>

                            <div class = "col-xs-9">
                                <div class = "row">
                                    <div class = "col-xs-9">Jitin Dominic</div>
                                    <div class = "col-xs-3"><a href = "#"><ins>Grails</ins></a></div>
                                </div>
                                <div class = "row">
                                    <div class = "col-xs-4 text-muted"><small>@jitin</small></div>
                                    <div class = "col-xs-4 text-muted">Subscriptions</div>
                                    <div class = "col-xs-4 text-muted">Post</div>
                                </div>
                                <div class = "row">
                                    <div class = "col-xs-4"><a href = "#"><ins>Subscribe</ins></a></div>
                                    <div class = "col-xs-4">50</div>
                                    <div class = "col-xs-4">30</div>
                                </div>
                            </div>
                        </div>

                        <div class = "row">
                            <div class = "col-xs-4">
                                <div class = "dropdown">
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
                            <div class = "col-xs-4">
                                <div class = "dropdown">
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
                            <div class = "col-xs-4">
                                <span><a href = "#"><ins>Edit</ins></a></span>&nbsp;
                                <span><a href = "#"><ins>Delete</ins></a></span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>