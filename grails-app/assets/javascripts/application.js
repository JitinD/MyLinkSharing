// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better 
// to create separate JavaScript files as needed.
//



    function ajaxSuccess(result) {

        if (result) {
            var jsonResponseDiv = $(".jsonResponse");

            if (result.message) {

                jsonResponseDiv.text(result.message);
                jsonResponseDiv.addClass("alert alert-success");
            }
            else {
                jsonResponseDiv.text(result.error);
                jsonResponseDiv.addClass("alert alert-danger");
            }
            jsonResponseDiv.css({'display': 'block'})
        }
    }


    $(document).ready(function () {

        $(".seriousness").change(function () {
            $.ajax({
                url: "/subscription/update",
                data: {topicId: $(this).attr('topicId'), serious: $(this).val()},
                success: ajaxSuccess
            });
        });


        $(".visibility").change(function () {
            $.ajax({
                url: "/topic/save",
                data: {topicName: $(this).attr('topicName'), visibility: $(this).val()},
                success: ajaxSuccess
            });
        });


        $("#saveTopicButton").click(function () {
            alert( $('#visibility').val() )
            $.ajax({
                url: "/topic/save",
                data: {topicName: $('#topicName').val(), visibility: $('#visibility').val()},
                success: location.reload()
            });
        });

        $(function () {
            $('#registrationForm').validate({
                rules: {
                    'firstName':{
                        required: true
                    },
                    'lastName':{
                        required: true
                    },
                    'password':{
                        required: true,
                        minlength :5
                    },
                    'confirmPassword':{
                        required: true,
                        confirm:true
                    },
                    'userName': {
                        required: true,
                        remote: {
                            url: "/login/validateUserName",
                            type: "post",
                            data: {
                                userName: function () {
                                    return $('#userName').val()
                                }
                            }
                        }
                    },
                    'emailID': {
                        required: true,
                        remote: {
                            url: "/login/validateEmail",
                            type: "post",
                            data: {
                                email: function () {
                                    return $('#emailID').val()
                                }
                            }
                        }
                    }
                },
                messages: {
                    'firstName':{
                        required: "First name can't be blank",
                    },
                    'lastName':{
                        required: "Last name can't be blank",
                    },
                    'password':{
                        required: "Password can't be blank",
                        minlength: "Password should be atleast 5 character long"
                    },
                    'confirmPassword':{
                        required: "Confirm password can't be blank"
                    },
                    'emailID': {
                        required: "Email address can't be blank",
                        remote: "Email address entered is already used"
                    },
                    'userName': {
                        required: "User name can't be blank",
                        remote: "User name entered already exist"
                    }
                }
            });

            jQuery.validator.addMethod("confirm", function (value, element) {
                var result = false;
                var password = $("#password").val();

                if (password === value) {
                    result = true;
                }
                return result;
            }, "Confirm password not matched with password");
        });
    });

