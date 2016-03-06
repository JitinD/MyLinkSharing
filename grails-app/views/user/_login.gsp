<div class="row">
    <div class="panel panel-default">
        <div class="panel-heading panelHeaders">
            <span class = "panelHeadersText">Login</span>
        </div>

        <div class="panel-body">
            <g:form class="form-horizontal" role="form">
                <div class="form-group row">
                    <label class="form-control-label col-sm-4">Username:</label>

                    <div class="col-sm-8">
                        <g:textField name="userName" class="form-control"/>
                    </div>
                </div>


                <div class="form-group row">
                    <label class="form-control-label col-sm-4">Password:</label>

                    <div class="col-sm-8">
                        <g:passwordField name="password" class="form-control"/>
                    </div>
                </div>

                <div class="form-group row">
                    <a href = "" class="control-label col-sm-6" data-toggle="modal" data-target="#forgotPasswordModal">
                        <ins>Forgot Password</ins>
                    </a>

                    <g:render template = "/user/forgotPassword" />

                    <div class="col-sm-6">
                        <g:submitButton class="btn btn-primary submitButtons" formaction="/login/loginHandler"
                                        type="submit" name="submit" value="Submit" />
                    </div>
                </div>
            </g:form>
        </div>
    </div>
</div>