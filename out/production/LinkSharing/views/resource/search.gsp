<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta name="layout" content="main">
        <title>Search Page</title>
    </head>

    <body>
    <div>
        <div class="row">
            <div class="col-xs-5">
                <div class="row">
                    <div class="panel panel-default">
                        <div class="panel-heading panelHeaders">
                            <span class = "panelHeadersText">Trending topics</span>
                        </div>

                        <div class="panel-body">
                            <g:each in="${trendingTopics}" var="topic">
                                <g:render template="/topic/show" model="[topic: topic]"/>
                            </g:each>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="panel panel-default">
                        <div class="panel-heading panelHeaders">
                            <span class = "panelHeadersText">Top posts</span>
                        </div>

                        <div class="panel-body">
                            <g:each in = "${topPosts}" var = "post">
                                <g:render template="/resource/show" model = "[post: post]" />
                            </g:each>
                        </div>
                    </div>

                </div>
            </div>

            <div class="col-xs-offset-1 col-xs-6">
                <div class="panel panel-default">
                    <div class="panel-heading panelHeaders">
                        <span class = "panelHeadersText">Posts:</span>
                    </div>

                    <div class = "panel-body">
                        <g:if test = "${posts}">
                            <g:each in = "${posts}" var = "post">
                                <g:render template = "/resource/show" model = "[post : post]" />
                            </g:each>
                        </g:if>
                        <g:else>
                            No results to be shown.
                        </g:else>
                    </div>
                </div>

            </div>
        </div>
    </div>
    </body>
</html>