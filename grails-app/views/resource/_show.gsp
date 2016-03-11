
<div class="row">
    <div class="col-xs-2">
        <ls:userImage id = "${post.userId}" />
        %{--<img src="" class="img img-thumbnail img-responsive image" />--}%
    </div>

    <div class="col-xs-10">
        <div class = "row">
            <div class = "col-xs-10">
                <g:link controller = "user" action = "profile" params = "[id: post.userId]">${post.user}</g:link>
                <small> (@${post.userName}) on ${post.createdDate}</small>
            </div>
            <div class = "col-xs-2"><a href="/topic/show?id=${post.topicId}">${post.topicName}</a></div>
        </div>

        <div class = "row">
            <div class = "col-xs-12">
                <div class="text-justify">${post.description}</div>
            </div>
        </div>

        <div class = "row">
            <div class = "col-xs-6">
                <a href="#"><span class="fa fa-facebook-square"></span></a>&nbsp;
                <a href="#"><span class="fa fa-tumblr"></span></a>&nbsp;
                <a href="#"><span class="fa fa-google-plus"></span></a>&nbsp;
            </div>
            <div class = "col-xs-6">
                <ls:markAsRead id = "${post.resourceId}" isRead = "${post.isRead}"> </ls:markAsRead>
                <ls:showResource id = "${post.resourceId}" url = "${post.url}" filePath = "${post.filePath}"></ls:showResource>
                <g:link controller = "resource" action = "show" params = "[id: post.resourceId]"><ins>View post</ins></g:link>
            </div>
        </div>
    </div>
</div>

<div class = "row">
    &nbsp;
</div>