<div class="modal fade" id="shareDocModal" tabindex="-1" role="dialog" aria-labelledby="sharedocModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="shareDocModalLabel">Share Document</h4>
            </div>

            <g:uploadForm class="form">
                <div class="modal-body table-responsive">
                    <table class="table table-condensed">
                        <tr>
                            <td>
                                <div class="form-group">
                                    <label for="doc">Document *</label>
                                </div>
                            </td>
                            <td>
                                <div class="form-group">

                                        <input type="file" id="filePath" name="filePath" required>

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
                                    <g:textArea name="description" id="description"/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="form-group">
                                    <label for="topicName">Topic *</label>
                                </div>
                            </td>
                            <td>
                                <div class="form-group">
                                    <div class="dropdown">
                                        <g:select name="topicName" from = "${topicNames}" optionKey="${it}"/>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="form-group">
                                    <g:submitButton class="btn btn-primary" formaction="/resource/saveDocResource"
                                                    type="submit" name="submit" value="Done"/>
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
            </g:uploadForm>
        </div>
    </div>
</div>]
