<div class="row">
    <div class="panel panel-default">
        <div class="panel-heading panelHeaders">
            <span class="panelHeadersText">Login</span>
        </div>

        <div class="panel-body">
            %{--<g:form id = "loginForm" name = "loginForm" class="form-horizontal" role="form" controller = "login" action="${postUrl}" method = "POST" autocomplete='off'>--}%
            <form action='${postUrl}' method='POST' id='loginForm' class='cssform' autocomplete='off'>
                <div class="form-group row">
                    <label class="form-control-label col-sm-4">Username:</label>

                    <div class="col-sm-8">
                        <g:textField id="username" name="j_username" class="form-control" required="required"/>
                    </div>

                </div>


                <div class="form-group row">
                    <label class="form-control-label col-sm-4">Password:</label>

                    <div class="col-sm-8">
                        <input type="password" id="password" name="j_password" class="form-control" required/>
                    </div>

                </div>

                <div class="form-group row">
                    <p id="remember_me_holder">
                        <input type='checkbox' class='chk' name='${rememberMeParameter}' id='remember_me'
                               <g:if test='${hasCookie}'>checked='checked'</g:if>/>
                        <label for='remember_me'>Remember me</label>
                    </p>
                </div>

                <div class="form-group row">
                    <a href="" class="control-label col-sm-6" data-toggle="modal" data-target="#forgotPasswordModal">
                        <ins>Forgot Password</ins>
                    </a>

                    <div class="col-sm-6">
                        <g:submitButton class="btn btn-primary submitButtons" type="submit" name="submit"
                                        value="Submit"/>
                    </div>
                </div>

                <div class="row">
                    <div class="alert-danger">${flash.loginError}</div>
                </div>

                %{--</g:form>--}%
            </form>
            <g:render template="/user/forgotPassword"/>
        </div>
    </div>
</div>

<script>

    (function () {
        document.forms['loginForm'].elements['j_username'].focus();
    })();

    $(function () {
        $('#loginForm').validate({
            rules: {
                'password': {
                    required: true,
                    minlength: 5
                },
                'username': {
                    required: true,
                    remote: {
                        url: "/login/validateUsernameForLogin",
                        type: "post"
                    }
                }
            },
            messages: {
                'password': {
                    required: "Password can't be blank",
                    minlength: "Password should be atleast 5 character long"
                },
                'username': {
                    required: "User name can't be blank",
                    remote: "Sorry, no such username found."
                }
            }
        });


        jQuery.validator.addMethod("confirm", function (value, element) {
            var result = false;
            var password = $('form#registrationForm input[id=password]').val();

            if (password === value) {
                result = true;
            }
            return result;
        }, "Confirm password not matched with password");
    });

</script>