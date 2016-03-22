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

    $(".markReadStatus").click(function (e) {
        e.preventDefault();

        var elm = $(this);
        var isRead = elm.attr('isRead');
        var resourceId = elm.attr('resourceId');

        $.ajax({
            url: "/readingItem/changeIsRead",
            data: {resourceId: resourceId, isRead: isRead},

            success: function(result){
                        ajaxSuccess(result);

                        if(isRead){
                            elm.removeClass("text-danger");
                            elm.addClass("text-success");
                            elm.text('Mark as unread');
                            elm.attr('isRead').val(false)
                        }
                        else {
                            elm.removeClass("text-success");
                            elm.addClass("text-danger");
                            elm.text('Mark as read');
                            elm.attr('isRead').val(true)
                        }
                    }
        });
    });

    $("#saveTopicButton").submit(function () {
        $.ajax({
            url: "/topic/save",
            data: {topicName: $('#topicName').val(), visibility: $('#visibility').val()},
            success: location.reload()
        });
    });

    $("#clearGlobalSearchPostBox").click(function () {
        $("#globalSearchPostBox").val("")
    });


    $('.modal').on('hidden.bs.modal', function(){
        $(this).find('form')[0].reset();
    });

    $('.fb_share_button').click(function(e){
        e.preventDefault();
        var id = $(this).attr('resourceId');
        var description = $(this).attr('resourceDescription');
        var topicName = $(this).attr('topicName');

        FB.ui(
            {
                method: 'feed',
                name: 'A new post on '+topicName,
                link: 'http://linksharing.cfapps.io/resource/show/'+id,
                description: description
            });
    });

});

