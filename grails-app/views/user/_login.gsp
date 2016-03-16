<div class="row">
    <div class="panel panel-default">
        <div class="panel-heading panelHeaders">
            <span class = "panelHeadersText">Login</span>
        </div>

        <div class="panel-body">
            <g:form id = "loginForm" class="form-horizontal" role="form" controller = "login" action="loginHandler">
                <div class="form-group row">
                    <label class="form-control-label col-sm-4">Username:</label>

                    <div class="col-sm-8">
                        <g:textField id = "userName" name="userName" class="form-control" required = "required"/>
                    </div>

                </div>


                <div class="form-group row">
                    <label class="form-control-label col-sm-4">Password:</label>

                    <div class="col-sm-8">
                        <input type = "password" id = "password" name="password" class="form-control" required/>
                    </div>

                </div>

                <div class="form-group row">
                    <a href = "" class="control-label col-sm-6" data-toggle="modal" data-target="#forgotPasswordModal">
                        <ins>Forgot Password</ins>
                    </a>

                    <div class="col-sm-6">
                        <g:submitButton class="btn btn-primary submitButtons" type="submit" name="submit" value="Submit" />
                    </div>
                </div>

                <div class = "row">
                    <div class="alert-danger">${flash.loginError}</div>
                </div>

            </g:form>

            <g:render template = "/user/forgotPassword" />
        </div>
    </div>
</div>