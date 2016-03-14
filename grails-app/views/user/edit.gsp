<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta name="layout" content="main">
        <title>User Edit bar</title>
    </head>

    <body>
        <div class="row">
            <div class="col-xs-4">

                <div class="row panel panel-default panel-body">
                    <g:render template = "/user/show" model = "[user : user]"/>
                    <g:hiddenField name="id" id = "id" value = "${user.userId}"/>
                </div>

                <div class = "row">
                    <div class="panel panel-default">
                        <div class="panel-heading panelHeaders">
                            <span class = "panelHeadersText">Topics</span>
                        </div>

                        <div id = "createdTopics" style = "overflow-y: auto; height: 300px">

                        </div>
                    </div>
                </div>

            </div>

            <div class="col-md-7 col-md-push-1">
                <div class="row">
                    <div class="panel panel-default">
                        <div class="panel-heading panelHeaders">
                            <span class="panelHeadersText">Edit profile</span>
                        </div>

                        <div class="panel-body">
                            <g:uploadForm class="form-horizontal" id = "updateForm" name = "updateForm" controller = "user" action = "save">
                                <div class="form-group row">
                                    <label class="form-control-label col-xs-4">First name*</label>

                                    <div class="col-xs-8">
                                        <input name = "firstName" id = "firstName" type="text"  value = "${user?.firstName}" class="form-control">
                                    </div>

                                    <div class="alert-danger"> <g:fieldError field="firstName" bean="${user}"/></div>
                                </div>


                                <div class="form-group row">
                                    <label class="form-control-label col-xs-4">Last name*</label>

                                    <div class="col-xs-8">
                                        <input name = "lastName" id = "lastName" type="text" value = "${user?.lastName}" class="form-control">
                                    </div>

                                    <div class="alert-danger"> <g:fieldError field="lastName" bean="${user}"/></div>
                                </div>

                                <div class="form-group row">
                                    <label class="form-control-label col-xs-4">Username*</label>

                                    <div class="col-xs-8">
                                        <input name = "userName" id = "userName" type="text" value = "${user?.userName}" class="form-control">
                                    </div>

                                    <div class="alert-danger"> <g:fieldError field="userName" bean="${user}"/></div>
                                </div>

                                <div class="form-group row">
                                    <label class="form-control-label col-xs-4">Photo</label>

                                    <div class="col-xs-5">
                                        <input type="file" id = "pic" name = "pic" class="form-control">
                                    </div>

                                </div>

                                <div class="form-group row">
                                    <div class="col-xs-offset-4">
                                        <input type="submit" class="btn btn-primary submitButtons" value="Update" />
                                    </div>
                                </div>
                            </g:uploadForm>
                        </div>
                    </div>
                </div>

                <div class = "row">
                    <div class="panel panel-default">
                        <div class="panel-heading panelHeaders">
                            <span class = "panelHeadersText">Change password</span>
                        </div>

                        <div class="panel-body">
                            <g:form class="form-horizontal" role="form" controller = "user" action="updatePassword">


                                <div class="form-group row">
                                    <label name class="form-control-label col-xs-4">Old password*</label>

                                    <div class="col-xs-8">
                                        <g:field type = "password" name = "oldPassword" id = "oldPassword" class="form-control" />
                                        <g:hiddenField name="id" id="id" value="${user.userId}"/>
                                    </div>

                                    <div class="alert-danger"> <g:fieldError field="password" bean="${user}"/></div>
                                </div>


                                <div class="form-group row">
                                    <label name class="form-control-label col-xs-4">Password*</label>

                                    <div class="col-xs-8">
                                        <g:field type = "password" name = "password" id = "password" class="form-control" />
                                    </div>

                                    <div class="alert-danger"> <g:fieldError field="password" bean="${user}"/></div>
                                </div>

                                <div class="form-group row">
                                    <label class="form-control-label col-xs-4">Confirm Password*</label>

                                    <div class="col-xs-8">
                                        <g:field type = "password" name = "confirmPassword" id = "confirmPassword" class="form-control" />
                                    </div>

                                    <div class="alert-danger"> <g:fieldError field="confirmPassword" bean="${user}"/></div>
                                </div>

                                <div class="form-group row">
                                    <div class="col-xs-offset-4">
                                        <input type="submit" class="btn btn-primary submitButtons" value="Update" />
                                    </div>
                                </div>

                            </g:form>

                        </div>
                    </div>
                </div>
            </div>
        </div>

        <g:javascript>
            $(document).ready(function(){
                $.ajax({
                    url: "/user/topics",
                    data:{id: $("#id").val()},
                    success: function(result){
                        $("#createdTopics").html(result)
                    }

                });
            });
        </g:javascript>

    </body>

</html>