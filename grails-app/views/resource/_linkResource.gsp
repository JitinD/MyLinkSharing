<div class="modal fade" id="shareLinkModal" tabindex="-1" role="dialog" aria-labelledby="sharelinkModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="sharelinkModalLabel">Share Link</h4>
            </div>

            <g:form class="form">
                <div class="modal-body table-responsive">
                    <table class="table table-condensed">
                        <tr>
                            <td>
                                <div class="form-group">
                                    <label for="url">Link *</label>
                                </div>
                            </td>
                            <td>
                                <div class="form-group">
                                    <g:field type="url" id="url" name="url" />
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="form-group">
                                    <label for="description">Description *</label>
                                </div>
                            </td>
                            <td>
                                <div class="form-group">
                                    <g:textArea id="description" name = "description" />
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="form-group">
                                    <label for="topic">Topic *</label>
                                </div>
                            </td>
                            <td>
                                <div class="form-group">
                                    <div class="dropdown">
                                        <g:select name="topic" from = "${topicNames}" optionKey="id" />
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="form-group">
                                    <g:submitButton class = "btn btn-primary" formaction="/resource/saveLinkResource" type = "submit" name = "submit" value = "Save" />
                                </div>
                            </td>
                            <td>
                                <div class="form-group">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
            </g:form>
        </div>
    </div>
</div>

