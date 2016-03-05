<div class="row">
    <div class="col-xs-2">
        <img src="" class="img img-thumbnail img-responsive" style="width:80px;height:70px">
    </div>

    <div class="col-xs-10">
        <div class = "row">
            <div class = "col-xs-10"><a href = "#">${post.user}</a><small> (@${post.userName}) on ${post.createdDate}</small></div>
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
                <g:link controller = "resource" action = "show" params = "[id: post.resourceId]">View post</g:link>
            </div>
        </div>
    </div>
</div>
