<div class="row">
    <div class="panel panel-default">
        <div class="panel-heading">
            Login
        </div>

        <div class="panel-body">
            <g:form class="form-horizontal" role="form">
                <div class="form-group">
                    <label class="control-label col-sm-2">Username:</label>

                    <div class="col-sm-10">
                        <g:textField name="userName" class="form-control"/>
                    </div>
                </div>


                <div class="form-group">
                    <label class="control-label col-sm-2">Password:</label>

                    <div class="col-sm-10">
                        <g:passwordField name="password" class="form-control"/>
                    </div>
                </div>

                <div class="form-group">
                    <a href="#" class="control-label col-sm-6"><ins>Forgot Password</ins></a>

                    <div class="col-sm-6">
                        <g:submitButton class="btn btn-primary" formaction="/login/loginHandler"
                                        type="submit" name="submit" value="Submit"/>
                    </div>
                </div>
            </g:form>
        </div>
    </div>
</div>