<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta name="layout" content="main">
        <title>Home Page</title>
    </head>

    <body>

            <div>
                <div class="row">

                    <div class="col-xs-6">
                        <div class="row">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    Recent Shares
                                </div>

                                <div class="panel-body">
                                    <g:each in = "${recentPosts}" var = "post">
                                        <g:render template="/resource/show" model = "[post: post]" />
                                    </g:each>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    Top posts
                                </div>

                                <div class="panel-body">
                                    <g:each in = "${topPosts}" var = "post">
                                        <g:render template="/resource/show" model = "[post: post]" />
                                    </g:each>
                                </div>
                            </div>

                        </div>

                    </div>

                    <div class="col-xs-1">

                    </div>

                    <div class="col-xs-5">
                        <g:render template="/user/login"/>
                        <g:render template="/user/register"/>
                    </div>
                </div>

            </div>
    </body>
</html>