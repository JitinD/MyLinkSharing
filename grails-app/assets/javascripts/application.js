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


    });

