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
        <asset:javascript src="jquery-2.2.1.min.js"/>
        <g:layoutHead/>

    </head>

    <body>

        <nav class = "navbar navbar-default navbar-static-top navBarHeader">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navBar"
                            aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <g:link controller = "login" action = "index" class = "navbar-brand navBarHeaderBrand">Link Sharing</g:link>
                </div>

                <div id="navBar">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <form class="form-inline navbar-form">
                                <div class="input-group">
                                    <span class="input-group-btn">
                                        <button class="btn btn-primary glyphicon glyphicon-search searchButtons">
                                        </button>
                                    </span>

                                    <input type="text" class="form-control input-group" placeholder="Search">

                                    <span class="input-group-btn">
                                        <button class="btn btn-primary glyphicon-searchphicon glyphicon-remove searchButtons">
                                        </button>
                                    </span>
                                </div>
                            </form>
                        </li>
                        <g:if test="${session.user}">
                            <li>
                                <a class="btn" data-toggle="modal" data-target="#createTopicModal">
                                    <span class="fa fa-weixin modalIcons"></span></a>
                            </li>

                            <li>
                                <a class="btn" data-toggle="modal" data-target="#sendInviteModal">
                                    <span class="glyphicon glyphicon-envelope modalIcons"></span></a>
                            </li>

                            <li>
                                <a class="btn" data-toggle="modal" data-target="#shareLinkModal">
                                    <span class="fa fa-link modalIcons"></span>
                                </a>
                            </li>

                            <li>
                                <a class="btn" data-toggle="modal" data-target="#shareDocModal">
                                    <span class="fa fa-file-o modalIcons"></span>
                                </a>
                            </li>

                            <li>
                                <div class="dropdown">
                                    <a class="btn dropdown-toggle" data-toggle="dropdown">
                                        <span class="glyphicon glyphicon-user"  style= "color: #4ba2f7; font-size: xx-large"></span>
                                        <span  class = "modalIcons">${session.user}</span>
                                        <span class="caret modalIcons"></span>
                                    </a>

                                    <ul class="dropdown-menu">
                                        <li>
                                            <g:link controller = "user" action = "profile" params = "[id: session.user.id]">
                                                Profile
                                            </g:link>
                                        </li>

                                        <li>
                                            <g:if test = "${session.user.isAdmin}">
                                                <g:link controller = "user" action = "list">
                                                    Users
                                                </g:link>
                                            </g:if>
                                        </li>

                                        <li>
                                            <g:link controller = "login" action = "logout">
                                                Logout
                                            </g:link>
                                        </li>
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

        <g:if test="${flash.message}">
            <div class="alert alert-success alert-dismissable">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                ${flash.message}
            </div>
        </g:if>


        <g:if test="${flash.error}">
            <div class="alert alert-danger alert-warning alert-dismissable">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                ${flash.error}
            </div>
        </g:if>


        <div class = "container">

            <g:layoutBody/>

        </div>

        <asset:javascript src="bootstrap.min.js"/>
        <asset:javascript src="jquery.validate.min.js"/>
        <asset:javascript src="application.js"/>

    </body>
</html>
