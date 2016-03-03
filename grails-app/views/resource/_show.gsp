<div class="row">
    <div class="col-xs-2">
        <img src="" class="img img-thumbnail img-responsive" style="width:100px;height:80px">
    </div>

    <div class="col-xs-10">

       <span>${post.createdBy} <small>@${post.createdBy.userName} ${post.dateCreated}</small></span>
        <span class="pull-right"><a href="/topic/show?id=${post.topic.id}"><ins>${post.topic}</ins></a></span>

        <div class="text-justify">${post.description}</div>

        <div>
            <span class="pull-left">
                <a href="#"><span class="fa fa-facebook-square" style="font-size:20px"></span></a>&nbsp;
                <a href="#"><span class="fa fa-tumblr" style="font-size:20px"></span></a>&nbsp;
                <a href="#"><span class="fa fa-google-plus" style="font-size:20px"></span></a>&nbsp;
                <!--a href="#" class="pull-right">Download</a>&nbsp;
                <a href="#" class="pull-right">View Full Site</a>&nbsp;
                <a href="#" class="pull-right">Mark as Read</a>&nbsp;-->
            </span>
            <h6 class="pull-right"><a href="#"><ins>View post</ins></a></h6>
        </div>
    </div>
</div>
