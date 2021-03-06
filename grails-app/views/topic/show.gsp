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
                        <div class="panel-heading panelHeaders">
                            <span  class = "panelHeadersText" style = "word-wrap: break-word">Topic: ${topic.name}</span>
                        </div>

                        <g:render template="/topic/show" model="[topic: topic]"/>

                    </div>

                    <div class="panel panel-default" >
                        <div class="panel-heading panelHeaders">
                            <span class = "panelHeadersText"  style = "word-wrap: break-word">Users: ${topic.name}</span>
                        </div>

                        <div class="panel panel-body" style = "overflow-y: auto; height: 250px;">
                            <g:each in = "${subscribedUsers}" var = "user">
                                <g:render template="/user/show" model = "[user : user]" />
                            </g:each>
                        </div>
                    </div>

                </div>

                <div class="col-xs-7">
                    <div class="panel panel-default">
                        <div class="panel-heading panelHeaders">
                            <span class = "panelHeadersText"  style = "word-wrap: break-word">Posts: ${topic.name}</span>

                            <form onsubmit="return false">
                                <div class="input-group">
                                    <span class="input-group-btn">
                                        <button id = "findSearchPostBox" topicId = "${topic.id}" class="btn btn-primary glyphicon glyphicon-search searchButtons" type="submit">
                                        </button>
                                    </span>

                                    <input type="text" id = "searchPostBox" class="form-control input-group" placeholder="Search">

                                    <span class="input-group-btn">
                                        <button id = "clearSearchPostBox" class="btn btn-primary glyphicon-searchphicon glyphicon-remove searchButtons">
                                        </button>
                                    </span>
                                </div>
                            </form>

                        </div>

                        <div id = "topicPosts" class="panel-body" style = "overflow-y: auto; height: 250px;">
                            <g:each in = "${topicPosts}" var = "post">
                                <g:render template = "/resource/show" model = "[post : post]" />
                            </g:each>

                            <g:paginate total = "${topicPostsCount}" controller = "topic" action = "show" max = "${searchCO.max}" offset = "${searchCO.offset}" />
                        </div>
                    </div>
                </div>
            </div>

        <script>

            $("#clearSearchPostBox").click(function () {
                $("#searchPostBox").val("")
            });

            $("#findSearchPostBox").click(function(){
                topicId = $(this).attr('topicId');

                $.ajax({
                    url: "/resource/search",
                    data: {q: $('#searchPostBox').val(), topicId: topicId},
                    success: function(result){
                        $("#topicPosts").html(result)
                    }
                });

            });
        </script>
    </body>
</html>