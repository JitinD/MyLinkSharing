<div class = "modal fade" id = "sendInviteModal" tabindex = "-1" role = "dialog" aria-labelledby = "sendinviteModal">
    <div class = "modal-dialog" role = "document">
        <div class = "modal-content">
            <div class = "modal-header">
                <h4 class = "modal-title" id = "sendInviteModalLabel">Send Invitation</h4>
            </div>
            <g:form class = "form">
                <div class = "modal-body table-responsive">
                    <table class = "table table-condensed">
                        <tr>
                            <td>
                                <div class = "form-group">
                                    <label for = "email">E-Mail *</label>
                                </div>
                            </td>
                            <td>
                                <div class = "form-group">
                                    <g:field type = "email" id = "email" name = "email" />
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class = "form-group">
                                    <label for = "topic">Topic *</label>
                                </div>
                            </td>
                            <td>
                                <div class = "form-group">
                                    <div class = "dropdown">
                                        <g:select name="topic" from = "${topicNames}" optionKey="id" />
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class = "form-group">
                                    <g:submitButton class = "btn btn-primary" type = "submit" name = "submit" value = "Invite" />
                                    <button type = "button" class = "btn btn-primary">Invite</button>
                                </div>
                            </td>
                            <td>
                                <div class = "form-group">
                                    <button type = "button" class = "btn btn-default" data-dismiss = "modal">Cancel</button>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
            </g:form>
        </div>
    </div>
</div>
