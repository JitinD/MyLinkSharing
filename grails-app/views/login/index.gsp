<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title></title>
</head>

<body>
<div class="container">
    <div>
        <div class="row">

            <div class="col-xs-6">
                <div class="row">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Recent Shares
                        </div>

                        <div class="panel-body">
                            <div class="row">
                                <div class="col-xs-2">
                                    <img src="" class="img-thumbnail" style="width:100; height:100" ;/>
                                </div>

                                <div class="col-xs-10">
                                    <h6>Uday Pratap Singh <small>@uday 5 min</small></h6>

                                    <div class="text-justify">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.</div>

                                    <div>
                                        <span class="pull-left">
                                            <i class="fa fa-facebook-official"></i>
                                            <i class="fa fa-twitter"></i>
                                            <i class="fa fa-google-plus"></i>
                                        </span>
                                        <h6 class="pull-right"><a href="#"><ins>View post</a></h6>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-xs-2">
                                    <img src="" class="img-thumbnail" style="width:100; height:100" ;/>
                                </div>

                                <div class="col-xs-10">
                                    <h6>Uday Pratap Singh <small>@uday 10 min</small></h6>

                                    <div class="text-justify">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.</div>

                                    <div>
                                        <span class="pull-left">
                                            <i class="fa fa-facebook-official"></i>
                                            <i class="fa fa-twitter"></i>
                                            <i class="fa fa-google-plus"></i>
                                        </span>
                                        <h6 class="pull-right"><a href="#"><ins>View post</a></h6>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Top posts
                            <button class="btn dropdown-toggle pull-right" data-toggle="dropdown" href="#">
                                Today<span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                                <li><a href="#">1 week</a></li>
                                <li><a href="#">1 month</a></li>
                                <li><a href="#">year</a></li>
                            </ul>
                        </div>

                        <div class="panel-body">
                            <div class="row">
                                <div class="col-xs-2">
                                    <img src="" class="img-thumbnail" style="width:100; height:100" ;/>
                                </div>

                                <div class="col-xs-10">
                                    <h6>Uday Pratap Singh <small>@uday 21 Jul 2014</small></h6>

                                    <div class="text-justify">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.</div>

                                    <div>
                                        <span class="pull-left">
                                            <i class="fa fa-facebook-official"></i>
                                            <i class="fa fa-twitter"></i>
                                            <i class="fa fa-google-plus"></i>
                                        </span>
                                        <h6 class="pull-right"><a href="#"><ins>View post</a></h6>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>

            </div>

            <div class="col-xs-1"></div>

            <div class="col-xs-5">
                <g:render template="/user/login"/>
                <g:render template="/user/register"/>
            </div>
        </div>

    </div>

</div>

</body>
</html>