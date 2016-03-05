<div class="modal fade" id="shareDocModal" tabindex="-1" role="dialog" aria-labelledby="sharedocModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header" style="background-color:#cfd7ff;">
                <h4 class="modal-title" id="shareDocModalLabel" style="color: #4ba2f7; font-weight: bold">
                    Share Document
                </h4>
            </div>

            <g:uploadForm class="form">
                <div class="modal-body table-responsive">

                    <div class="form-group row">
                        <label class="form-control-label col-xs-4">Document*</label>

                        <div class="col-xs-4">
                            <input type="text" class="form-control">
                        </div>

                        <div class="col-xs-2">
                            <input type="button" id="filePath" name="filePath" class="btn btn-primary" value="Browse"
                                   style="background-color:#4ba2f7;"/>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="form-control-label col-xs-4">Description*</label>

                        <div class="col-xs-6">
                            <g:textArea id="description" name="description" class="form-control"/>
                        </div>
                    </div>


                    <div class="form-group row">
                        <label name="topic" id="topic" class="form-control-label col-xs-4">
                            Topic*
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
                            <g:submitButton class="btn btn-primary" formaction="#" type="submit" name="submit"
                                            value="Share" style="background-color:#4ba2f7;"/>
                        </div>

                        <div class="col-xs-4">
                            <button type="button" class="btn btn-primary" data-dismiss="modal"
                                    style="background-color:#4ba2f7;">Close</button>
                        </div>
                    </div>
                </div>
            </g:uploadForm>
        </div>
    </div>
</div>]
