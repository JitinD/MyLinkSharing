
<div class="row">
    <div class="col-xs-2">
        <ls:userImage id = "${post.userId}" />
    </div>

    <div class="col-xs-10">
        <div class = "row">
            <div class = "col-xs-10">
                <g:link controller = "user" action = "profile" params = "[id: post.userId]">${post.user}</g:link>
                <small>
                    (@${post.userName}) on
                    <g:formatDate format="dd-MM-yyyy" date="${post.createdDate}" style="MEDIUM"/>
                </small>
            </div>
            <div class = "col-xs-2 text-right topicNameClass"><a style = "width: 100%"  title = "${post.topicName}" href="/topic/show?id=${post.topicId}">${post.topicName}</a></div>
        </div>

        <div class = "row">
            <div class = "col-xs-12">
                <div id = "postDescription" class="text-justify">${post.description}</div>
            </div>
        </div>

        <div class = "row">
            <div class = "col-xs-6">

                <a href="" class = "fb_share_button" resourceId = "${post.resourceId}" resourceDescription = "${post.description}"
                   topicName = "${post.topicName}">
                    <span class="fa fa-facebook-square"></span>
                </a>&nbsp;

                <a class="twitter-share-button" href="https://twitter.com/intent/tweet?text=${post.description}&hashtags=${post.topicName}&url=http://127.0.0.1:8080/resource/show/${post.resourceId}"
                   onclick="window.open(this.href, '', 'menubar=no, toolbar=no,resizable=yes,scrollbars=yes,height=600,width=600');return false;">
                    <span class="fa fa-twitter-square"></span>
                </a>

                <a href="https://plus.google.com/share?url={http://127.0.0.1:8080/resource/show/${post.resourceId}" onclick="window.open(this.href,
                        '', 'menubar=no,toolbar=no,resizable=yes,scrollbars=yes,height=600,width=600');return false;">
                    <img src="https://www.gstatic.com/images/icons/gplus-16.png" alt="Share on Google+"/>
                </a>

            </div>

            <div class = "col-xs-6">
                <g:if test="${controllerName == 'user' && actionName == 'index'}">
                    <ls:markAsRead id = "${post.resourceId}" isRead = "${post.isRead}"> </ls:markAsRead>&nbsp;
                </g:if>
                <ls:showResource id = "${post.resourceId}" url = "${post.url}" filePath = "${post.filePath}"></ls:showResource>&nbsp;
                <g:link controller = "resource" action = "show" params = "[id: post.resourceId]"><ins>View post</ins></g:link>
            </div>
        </div>
    </div>
</div>

<div class = "row">
    &nbsp;
</div>
