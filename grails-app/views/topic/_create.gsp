<div class="modal fade" id="createTopicModal" tabindex="-1" role="dialog" aria-labelledby="createTopicModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header panelHeaders">
                <h4 class="modal-title panelHeadersText" id="createTopicModalLabel">
                    Create Topic
                </h4>
            </div>
            <g:form class="form form-horizontal" controller="topic" action="save">
                <div class="modal-body table-responsive">
                    <div class="form-group row">
                        <label class="form-control-label col-xs-4">Name*</label>

                        <div class="col-xs-6">
                            <input type="text" id="topicName" name="topicName" class="form-control" required/>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for = "visibility" class="form-control-label col-xs-4">
                            Visibility *
                        </label>

                        <div class="col-xs-8">
                                <g:select id = "visibility" name="visibility" from="${enums.Visibility.values()}"
                                          class="btn btn-default btn-sm dropdown-toggle"/>
                        </div>
                    </div>

                    <div class="form-group row">
                        <div class="col-xs-offset-4 col-xs-4">
                            <g:submitButton id = "saveTopicButton" name = "saveTopicButton" class="btn btn-primary submitButtons"
                                             value="Save"/>
                        </div>

                        <div class="col-xs-4">
                            <button type="button" class="btn btn-primary submitButtons"
                                    data-dismiss="modal">Close</button>
                        </div>

                    </div>
                </div>
            </g:form>
        </div>
    </div>
</div>

<script>
    $(function () {
        $('#createTopicModal').validate({
            rules: {
                'topicName': {
                    required: true,
                    remote: {
                        url: "/topic/validateTopicNameForSessionUser",
                        type: "post"
                    }
                }
            },
            messages: {
                'topicName': {
                    required: "Topic name can't be blank.",
                    remote: "Topic name already exist."
                }
            }
        });
    });

</script>