<div class="panel-body">
    <div class="row">
        <div class="col-xs-3">
            <img src="" class="img img-thumbnail img-responsive image" />
        </div>

        <div class="col-xs-9">
            <div class="row">
                <div class="col-xs-8"><a href = "#">${topic.createdBy.name}</a></div>
                <div class="col-xs-4"><a href="#">${topic.name}</a></div>
            </div>

            <div class="row">
                <div class="col-xs-4 text-muted"><small>(@${topic.createdBy.userName})</small></div>
                <div class="col-xs-4 text-muted">Subscriptions</div>
                <div class="col-xs-4 text-muted">Post</div>
            </div>

            <div class="row">
                <ls:showSubscribe id = "${topic.id}" />
                <div class="col-xs-4"><ls:subscriptionCount topicId = "${topic.id}" /></div>
                <div class="col-xs-4"><ls:resourceCount topicId = "${topic.id}" /></div>
            </div>
        </div>
    </div>
</div>

<div class="panel panel-footer">
    <div class="row">
        <div class="col-xs-4">
            <div class="dropdown">
                <button class="btn btn-default btn-sm dropdown-toggle" type="button" data-toggle="dropdown">
                    Serious
                    <span class = "caret"></span>
                </button>

                <ul class="dropdown-menu">
                    <li><a href="#">Serious</a></li>
                    <li><a href="#">Casual</a></li>
                </ul>
            </div>
        </div>

        <div class="col-xs-4">
            <div class="dropdown">
                <button class="btn btn-default btn-sm dropdown-toggle" type="button" data-toggle="dropdown">
                    Public
                    <span class = "caret"></span>
                </button>

                <ul class="dropdown-menu">
                    <li><a href="#">Public</a></li>
                    <li><a href="#">Private</a></li>
                </ul>
            </div>
        </div>

        <div class="col-xs-4">
            <div class = "row">
                <div class = "col-xs-4"><a href="#"><ins>Edit</ins></a></div>
                <div class = "col-xs-8"><a href="#"><ins>Delete</ins></a></div>
            </div>
        </div>
    </div>
</div>
