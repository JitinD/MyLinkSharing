<div class="modal fade" id="sendInviteModal" tabindex="-1" role="dialog" aria-labelledby="sendinviteModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header panelHeaders">
                <h4 class="modal-title panelHeadersText" id="sendInviteModalLabel">
                    Send Invitation
                </h4>
            </div>
            <g:form class="form">
                <div class="modal-body table-responsive">

                    <div class="form-group row">
                        <label class="form-control-label col-xs-4">Email*</label>

                        <div class="col-xs-6">
                            <g:field type="email" id="email" name="email" class="form-control"/>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label name="topic" id="topic" class="form-control-label col-xs-4">
                            Topic *
                        </label>

                        <div class="col-xs-8">
                            <div class="dropdown">
                                <g:select name="topic" from="${topicNames}" optionKey="id"
                                          class="btn btn-default btn-sm dropdown-toggle"/>
                            </div>
                        </div>
                    </div>

                    <div class="form-group row">
                        <div class="col-xs-offset-4 col-xs-4">
                            <g:submitButton class="btn btn-primary submitButtons" formaction="#" type="submit"
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
