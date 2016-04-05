<%@ page contentType="text/html;charset=UTF-8" %>
<html>

    <head>
        <meta name="layout" content="main">
        <title>Resource Title bar</title>
        <asset:stylesheet src="jquery.rateyo.css"/>
        <asset:javascript src="jquery.rateyo.js"/>
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

                                    <div class="col-xs-2 topicNameClass">
                                        <a style = "width: 100%"  title = "${post.topicName}" href="/topic/show?id=${post.topicId}">
                                            ${post.topicName}
                                        </a>
                                    </div>
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

                                            <div id="rateYo"></div>

                                            <input type="hidden" id="default-hidden-resource-rating" value="${post?.score}"/>
                                            <input type="hidden" id="hidden-resource-id" value="${post?.resourceId}"/>

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
            });

            $(function () {
                $("#rateYo").rateYo({
                    fullStar: true,
                    onSet: function (rating, rateYoInstance) {
                        $.ajax({
                            url: "/resourceRating/save",
                            data: {
                                resourceId: $("#hidden-resource-id").val(), score: rating
                            }
                        })
                    }
                });
                $("#rateYo").rateYo("rating", $("#default-hidden-resource-rating").val());
            });


            (function ($) {
                $.fn.rating = function (method, options) {
                    method = method || 'create';
                    var settings = $.extend({
                        limit: 5,
                        value: 0,
                        glyph: "glyphicon-star",
                        coloroff: "gray",
                        coloron: "gold",
                        size: "2.0em",
                        cursor: "default",
                        onClick: function () {
                        },
                        endofarray: "idontmatter"
                    }, options);
                    var style = "";
                    style = style + "font-size:" + settings.size + "; ";
                    style = style + "color:" + settings.coloroff + "; ";
                    style = style + "cursor:" + settings.cursor + "; ";

                    if (method == 'create') {
                        this.each(function () {
                            attr = $(this).attr('data-rating');
                            if (attr === undefined || attr === false) {
                                $(this).attr('data-rating', settings.value);
                            }
                        });

                        for (var i = 0; i < settings.limit; i++) {
                            this.append('<span data-value="' + (i + 1) + '" class="ratingicon glyphicon ' + settings.glyph + '" style="' + style + '" aria-hidden="true"></span>');
                        }

                        this.each(function () {
                            paint($(this));
                        });
                    }

                    if (method == 'set') {
                        this.attr('data-rating', options);
                        this.each(function () {
                            paint($(this));
                        });
                    }

                    if (method == 'get') {
                        return this.attr('data-rating');
                    }

                    this.find("span.ratingicon").click(function () {
                        rating = $(this).attr('data-value');
                        $(this).parent().attr('data-rating', rating);
                        paint($(this).parent());
                        settings.onClick.call($(this).parent());
                    });

                    function paint(div) {
                        rating = parseInt(div.attr('data-rating'));
                        div.find("input").val(rating);
                        div.find("span.ratingicon").each(function () {
                            var rating = parseInt($(this).parent().attr('data-rating'));
                            var value = parseInt($(this).attr('data-value'));
                            if (value > rating) {
                                $(this).css('color', settings.coloroff);
                            }
                            else {
                                $(this).css('color', settings.coloron);
                            }
                        })
                    }
                };
            }(jQuery));

            $(document).ready(function () {
                $("#stars-default").rating();
            });


        </script>
    </body>
</html>