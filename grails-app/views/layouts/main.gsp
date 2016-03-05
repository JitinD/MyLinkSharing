<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title><g:layoutTitle default="Grails"/></title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        %{--<asset:stylesheet src="application.css"/>--}%
        <asset:stylesheet src="myCSS.css"/>
        <asset:stylesheet src="font-awesome.min.css"/>
        <asset:stylesheet src="bootstrap.min.css"/>

        %{--<asset:javascript src="application.js"/>--}%
        <asset:javascript src="jquery-2.2.1.min.js"/>
        <asset:javascript src="bootstrap.min.js"/>

        <style>

        </style>
        <g:layoutHead/>

    </head>

    <body>

        <nav class = "navbar navbar-default navbar-static-top" style = "background-color:#cfd7ff; height : 70px">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navBar"
                            aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <g:link controller = "login" action = "index" class = "navbar-brand" style = "color: #4ba2f7;font-size: x-large; font-weight: bolder">Link Sharing</g:link>
                </div>

                <div id="navBar">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <form class="form-inline navbar-form">
                                <div class="input-group">
                                    <span class="input-group-btn">
                                        <button class="btn btn-primary glyphicon glyphicon-search" style = "background-color:#4ba2f7;">
                                        </button>
                                    </span>

                                    <input type="text" class="form-control input-group" placeholder="Search">

                                    <span class="input-group-btn">
                                        <button class="btn btn-primary glyphicon-searchphicon glyphicon-remove" style = "background-color:#4ba2f7;">
                                        </button>
                                    </span>
                                </div>
                            </form>
                        </li>
                        <g:if test="${session.user}">
                            <li>
                                <a class="btn" data-toggle="modal" data-target="#createTopicModal">
                                    <span class="fa fa-weixin" style= "color: #4ba2f7; font-size: x-large"></span></a>
                            </li>

                            <li>
                                <a class="btn" data-toggle="modal" data-target="#sendInviteModal">
                                    <span class="glyphicon glyphicon-envelope"  style= "color: #4ba2f7; font-size: x-large"></span></a>
                            </li>

                            <li>
                                <a class="btn" data-toggle="modal" data-target="#shareLinkModal">
                                    <span class="fa fa-link"  style= "color: #4ba2f7; font-size: x-large"></span>
                                </a>
                            </li>

                            <li>
                                <a class="btn" data-toggle="modal" data-target="#shareDocModal">
                                    <span class="fa fa-file-o"  style= "color: #4ba2f7; font-size: x-large"></span>
                                </a>
                            </li>

                            <li>
                                <div class="dropdown">
                                    <a class="btn dropdown-toggle" data-toggle="dropdown">
                                        <span class="glyphicon glyphicon-user"  style= "color: #4ba2f7; font-size: xx-large"></span>
                                        <span  style= "color: #4ba2f7; font-size: large">${session.user}</span>
                                        <span class="caret" style= "color: #4ba2f7; font-size: large"></span>
                                    </a>

                                    <ul class="dropdown-menu">
                                        <li><a href="#">Profile</a></li>
                                        <li><a href="/login/logout">Logout</a></li>
                                    </ul>
                                </div>
                            </li>
                        </g:if>
                    </ul>
                </div>
            </div>
        </nav>

        <g:if test="${session.user}">
            <g:render template="/topic/create"/>
            <g:render template="/topic/email"/>
            <g:render template="/resource/docResource"/>
            <g:render template="/resource/linkResource"/>
        </g:if>

        %{--

        <g:if test = "${flash.message}">
            ${flash.message}
        </g:if>

        <g:if test="${flash.error}">
            ${flash.error}
        </g:if>

        --}%

        <div class = "container">
            <g:layoutBody/>
        </div>

    </body>
</html>
