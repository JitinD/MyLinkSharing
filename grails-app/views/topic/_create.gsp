<div class = "modal fade" id = "createTopicModal" tabindex = "-1" role = "dialog" aria-labelledby = "createtopicModal">
    <div class = "modal-dialog" role = "document">
        <div class = "modal-content">
            <div class = "modal-header">
                <h4 class = "modal-title" id = "createTopicModalLabel">Create Topic</h4>
            </div>
            <g:form class = "form">
                <div class = "modal-body table-responsive">
                    <table class = "table table-condensed">
                        <tr>
                            <td>
                                <div class = "form-group">
                                    <label for = "topicName">Name *</label>
                                </div>
                            </td>
                            <td>
                                <div class = "form-group">
                                    <g:textField id = "topicName" name = "topicName" />
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class = "form-group">
                                    <label name = "visibility" id = "visibility">Visibility *</label>
                                </div>
                            </td>
                            <td>
                                <div class = "form-group">
                                    <div class = "dropdown">
                                        <g:select name = "visibility" from = "${enums.Visibility.values()}" />
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class = "form-group">
                                    <g:submitButton class = "btn btn-primary" formaction="/topic/save" type = "submit" name = "submit" value = "Save" />
                                </div>
                            </td>
                            <td>
                                <div class = "form-group">
                                    <button type = "button" class = "btn btn-default" data-dismiss = "modal">Close</button>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
            </g:form>
        </div>
    </div>
</div>
