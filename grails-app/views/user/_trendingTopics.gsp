<div class="panel panel-default">

    <div class="panel-heading">
        Trending topics
    </div>

    <g:each in="${topicVoList}" var="topicVo">
        <div class="panel-body">
            <div class="row">
                <div class="col-xs-3">
                    <img src="" class="img img-thumbnail img-responsive" style="width:80px; height:70px;"/>
                </div>

                <div class="col-xs-9">
                    <div class="row">
                        <div class="col-xs-7">${topicVo.createdBy.name}</div>

                        <div class="col-xs-5"><a href="#"><ins>${topicVo.name}</ins></a></div>
                    </div>

                    <div class="row">
                        <div class="col-xs-4 text-muted"><small>@${topicVo.createdBy.userName}</small></div>

                        <div class="col-xs-4 text-muted">Subscriptions</div>

                        <div class="col-xs-4 text-muted">Post</div>
                    </div>

                    <div class="row">
                        <div class="col-xs-4"><a href="#"><ins>Subscribe</ins></a></div>

                        <div class="col-xs-4">50</div>

                        <div class="col-xs-4">30</div>
                    </div>
                </div>
            </div>
        </div>

        <div class="panel panel-footer">
            <div class="row">
                <div class="col-xs-4">
                    <div class="dropdown">
                        <button class="btn btn-default btn-sm dropdown-toggle" type="button"
                                data-toggle="dropdown">Serious
                            <span class="caret"></span>
                        </button>

                        <ul class="dropdown-menu">
                            <li><a href="#">Serious</a></li>
                            <li><a href="#">Casual</a></li>
                        </ul>
                    </div>
                </div>

                <div class="col-xs-4">
                    <div class="dropdown">
                        <button class="btn btn-default btn-sm dropdown-toggle" type="button"
                                data-toggle="dropdown">Public
                            <span class="caret"></span>
                        </button>

                        <ul class="dropdown-menu">
                            <li><a href="#">Public</a></li>
                            <li><a href="#">Private</a></li>
                        </ul>
                    </div>

                </div>

                <div class="col-xs-4">
                    <span><a href="#"><ins>Edit</ins></a></span>&nbsp;
                    <span><a href="#"><ins>Delete</ins></a></span>
                </div>
            </div>
        </div>
    </g:each>

</div>


