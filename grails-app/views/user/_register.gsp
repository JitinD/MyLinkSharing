<div class="row">
    <div class="panel panel-default">
        <div class="panel-heading panelHeaders">
            <span class="panelHeadersText">Register</span>
        </div>

        <div class="panel-body">
            <g:uploadForm class="form-horizontal" name = "registerForm" controller = "login" action = "register">
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
                    <label class="form-control-label col-xs-4">Email*</label>

                    <div class="col-xs-8">
                        <input name = "emailID" id = "emailID" type="text" value = "${user?.emailID}" class="form-control">
                    </div>

                    <div class="alert-danger"> <g:fieldError field="emailID" bean="${user}"/></div>
                </div>

                <div class="form-group row">
                    <label class="form-control-label col-xs-4">Username*</label>

                    <div class="col-xs-8">
                        <input name = "userName" id = "userName" type="text" value = "${user?.userName}" class="form-control">
                    </div>

                    <div class="alert-danger"> <g:fieldError field="userName" bean="${user}"/></div>
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
                    <label class="form-control-label col-xs-4">Photo</label>

                    <div class="col-xs-5">
                        <input type="file" id = "pic" name = "pic" class="form-control">
                    </div>

                </div>

                <div class="form-group row">
                    <div class="col-xs-offset-4">
                        <input type="submit" class="btn btn-primary submitButtons" value="Register" />
                    </div>
                </div>
            </g:uploadForm>
        </div>
    </div>
</div>
