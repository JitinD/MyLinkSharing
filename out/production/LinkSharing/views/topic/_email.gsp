<div class="modal fade" id="sendInviteModal" tabindex="-1" role="dialog" aria-labelledby="sendinviteModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header panelHeaders">
                <h4 class="modal-title panelHeadersText" id="sendInviteModalLabel">
                    Send Invitation
                </h4>
            </div>
            <g:form name = "sendInviteForm" class="form sendInviteModal" controller = "topic" action = "invite">
                <div class="modal-body table-responsive">

                    <div class="form-group row">
                        <label class="form-control-label col-xs-4">Email*</label>

                        <div class="col-xs-6">
                            <input type="email" id="emailID" name="emailID" class="form-control" required/>
                        </div>
                    </div>

                    <div id = "subscribedTopicsList" class="form-group row">
                        <label class="form-control-label col-xs-4">
                            Topic *
                        </label>

                        <div class="col-xs-8">
                            <ls:showSubscribedTopics />
                        </div>
                    </div>

                    <div class="form-group row">
                        <div class="col-xs-offset-4 col-xs-4">
                            <g:submitButton class="btn btn-primary submitButtons" type="submit"
                                            name="submit" value="Invite"/>
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
        $('#sendInviteForm').validate({
            rules: {
                'emailID': {
                    required: true,
                    remote: {
                        url: "/user/validateEmailForInvitation",
                        type: "post"
                    },
                    'name':{
                        required: true
                    }
                }
            },
            messages: {
                'emailID': {
                    required: "Email id can't be blank.",
                    remote: "Email id doesn't belong to a registered user."
                },
                'name':{
                    required: "Topic can't be empty."
                }
            }
        });
    });

</script>