<%@ page contentType="text/html;charset=UTF-8" %>
<html>

    <head>
        <meta name="layout" content="main">
        <title>Resource Title bar</title>
    </head>

    <body>
        <div class="row">
            <div class="col-xs-7">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-xs-2">
                                <ls:userImage id = "${post.userId}" />
                            </div>

                            <div class="col-xs-10">
                                <div class="row">
                                    <div class="col-xs-10">${post.user}</div>

                                    <div class="col-xs-2"><a href="/topic/show?id=${post.topicId}"><ins>${post.topicName}</ins>
                                    </a></div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-8 text-muted"><small>(@${post.userName})</small></div>

                                    <div class="col-xs-offset-1 col-xs-3">
                                        <g:formatDate format="dd-MM-yyyy" date="${post.createdDate}" style="MEDIUM"/>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-8"></div>

                                    <div class="col-xs-4">

                                        <ls:canRate id = "${post.resourceId}" score="${post.score}">
                                            <g:form controller="resourceRating" action="save"
                                                    params="['resourceId': post.resourceId]">

                                                <g:select name="score" from="${[1, 2, 3, 4, 5]}" optionKey="${it}"
                                                          value="${post.score}" class="btn btn-default btn-sm dropdown-toggle"/>
                                                &nbsp;
                                                <g:submitButton class="btn btn-primary submitButtons" type="submit"
                                                                name="submit" value="Vote"/>
                                            </g:form>
                                        </ls:canRate>

                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-xs-12">
                                ${post.description}
                            </div>
                        </div>
                    </div>

                    <div class="panel-footer">
                        <div class="row">

                            <div class="col-xs-2">
                                <div class="row">
                                    <div class="col-xs-1">
                                        <a href="#"><span class="fa fa fa-facebook-square"></span></a>
                                    </div>

                                    <div class="col-xs-1">
                                        <a href="#"><span class="fa fa-twitter-square"></span></a>
                                    </div>

                                    <div class="col-xs-1">
                                        <a href="#"><span class="fa fa-google-plus-square"></span></a>
                                    </div>
                                </div>
                            </div>

                            <div class="col-xs-10">
                                <div class="row">
                                    <div class="col-xs-offset-6 col-xs-2">
                                        <ls:canDeleteResoure id="${post.resourceId}">
                                            <a id = 'resourceDelete' href = "" data-target = "#deleteResourceConfirmationModal" data-toggle="modal" postId = ${post.resourceId}><span class = 'glyphicon glyphicon-trash panelIcons'></span></a>
                                        </ls:canDeleteResoure>
                                    </div>

                                    <div class="col-xs-2">
                                        <ls:showEdit id = "${post.resourceId}">
                                            <a href = "" class="editDescription" postId = "${post.resourceId}" postDescription = "${post.description}" name = "editDescription" data-toggle="modal" data-target="#editDescriptionModal">
                                                <span class = "glyphicon glyphicon-edit panelIcons"></span>
                                            </a>
                                        </ls:showEdit>
                                    </div>

                                    <div class="col-xs-2">
                                        <ls:showResource id="${post.resourceId}" url="${post.url}" filePath="${post.filePath}"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-xs-5">
                <div class="panel panel-default">
                    <div class="panel-heading  panelHeaders">
                        Trending topics
                    </div>
                    <div style = "overflow-y: auto; height: 300px">
                        <g:each in="${trendingTopics}" var="topic">
                            <g:render template="/topic/show" model="[topic: topic]"/>
                        </g:each>
                    </div>
                </div>
            </div>


            <g:render template="/resource/edit"/>
            <g:render template="/resource/confirmation" />
        </div>

        <script>

            $(".editDescription").click(function(){

                var description = $(this).attr('postDescription');
                var id = $(this).attr('postId');

                $("#editDescriptionModal #description").val(description);
                $("#editDescriptionModal #id").val(id)
            });


            $("#resourceDelete").click(function(){
                var postId = $(this).attr('postId')

                $("#deleteResourceConfirmationModal #id").val(postId)
            })

        </script>
    </body>
</html>