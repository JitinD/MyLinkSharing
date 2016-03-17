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

        $.ajax({
            url: "/readingItem/changeIsRead",
            data: {resourceId: $(this).attr('resourceId'), isRead: $(this).attr('isRead')},
            success: /*function(){
                if($(this).attr('isRead'))
                    $("#readStatus").html("<a href = '' class = 'markReadStatus text-success' resourceId = ${resourceId} isRead = ${isRead}>Mark as unread</a>")
                else
                    $("#readStatus").html("<a href = '' class = 'markReadStatus text-danger' resourceId = ${resourceId} isRead = ${isRead}>Mark as read</a>")
            }*/location.reload()
        });
    });

    $(".saveTopicNameButton").click(function(){
        topicId = $(this).attr('topicId');

        $.ajax({
            url: "/topic/save",
            data: {topicName: $("#oldTopicName"+topicId).val(), newTopicName: $("#newTopicName"+topicId).val()},
            success:function(result){
                ajaxSuccess(result);
                location.reload();
            }

        })
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

});

