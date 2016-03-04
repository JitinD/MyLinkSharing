<div class="row">
    <div class="col-xs-2">
        <img src="" class="img img-thumbnail img-responsive" style="width:100px;height:80px">
    </div>

    <div class="col-xs-10">

       <span>${post.user} <small>@${post.userName} ${post.createdDate}</small></span>
        <span class="pull-right"><a href="/topic/show?id=${post.topicId}"><ins>${post.topicName}</ins></a></span>

        <div class="text-justify">${post.description}</div>

        <div>
            <span class="pull-left">
                <a href="#"><span class="fa fa-facebook-square" style="font-size:20px"></span></a>&nbsp;
                <a href="#"><span class="fa fa-tumblr" style="font-size:20px"></span></a>&nbsp;
                <a href="#"><span class="fa fa-google-plus" style="font-size:20px"></span></a>&nbsp;
                <ls:showResource id = "${post.resourceId}" url = "${post.url}" filePath = "${post.filePath}"></ls:showResource>
                <ls:markAsRead id = "${post.resourceId}" isRead = "${post.isRead}"> </ls:markAsRead>
            </span>
            <h6 class="pull-right"><g:link controller = "resource" action = "show" params = "[id: post.resourceId]"><ins>View post</ins></g:link></h6>
        </div>
    </div>
</div>



%{--

<div class="row">
    <div class="col-md-3">
        <img src="" class="img img-thumbnail img-responsive"
             style="width:150px;height:150px">
    </div>

    <div class="col-md-9 panel">
        <span class="text-primary">Uday Pratap Singh</span>
        <span class="text-muted">@uday 5 min</span>
        <span class="text-primary pull-right">Grails</span>

        <div class="panel-body text-justify">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis
        </div>

        <div class="panel-footer">
            <a href="#"><span class="fa fa-facebook-square" style="font-size:20px"></span>
            </a>
            <a href="#"><span class="fa fa-tumblr" style="font-size:20px"></span></a>
            <a href="#"><span class="fa fa-google-plus" style="font-size:20px"></span></a>
            <a href="#" class="pull-right">Download</a>
            <a href="#" class="pull-right">View Full Site</a>
            <a href="#" class="pull-right">Mark as Read</a>
            <a href="#" class="pull-right">View Post</a>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-md-3">
        <img src="" class="img img-thumbnail img-responsive"
             style="width:150px;height:150px">
    </div>

    <div class="col-md-9 panel">
        <span class="text-primary">Uday Pratap Singh</span>
        <span class="text-muted">@uday 10 min</span>
        <span class="text-primary pull-right">Grails</span>

        <div class="panel-body text-justify">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis
        </div>

        <div class="panel-footer">
            <a href="#"><span class="fa fa-facebook-square" style="font-size:20px"></span>
            </a>
            <a href="#"><span class="fa fa-tumblr" style="font-size:20px"></span></a>
            <a href="#"><span class="fa fa-google-plus" style="font-size:20px"></span></a>
            <a href="#" class="pull-right">Download</a>
            <a href="#" class="pull-right">View Full Site</a>
            <a href="#" class="pull-right">Mark as Read</a>
            <a href="#" class="pull-right">View Post</a>
        </div>
    </div>
</div>--}%
