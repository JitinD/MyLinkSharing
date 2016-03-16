<div class="modal fade" id="shareDocModal" tabindex="-1" role="dialog" aria-labelledby="sharedocModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header panelHeaders">
                <h4 class="modal-title panelHeadersText" id="shareDocModalLabel">
                    Share Document
                </h4>
            </div>

            <g:uploadForm class="form" controller = "documentResource" action = "save">
                <div class="modal-body table-responsive">

                    <div class="form-group row">
                        <label class="form-control-label col-xs-4">Document*</label>

                        <div class="col-xs-4">
                            <input type="file" name = "file" class="form-control" accrequired = "required">
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
                                <ls:showSubscribedTopics />
                        </div>
                    </div>

                    <div class="form-group row">
                        <div class="col-xs-offset-4 col-xs-4">
                            <g:submitButton class="btn btn-primary submitButtons" type="submit"
                                            name="submit" value="Share"/>
                        </div>

                        <div class="col-xs-4">
                            <button type="button" class="btn btn-primary submitButtons"
                                    data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </g:uploadForm>
        </div>
    </div>
</div>



<script>
    $(function () {
        $('#shareDocModal').validate({
            rules: {
                'file': {
                    required: true
                }
            },
            messages: {
                'file': {
                    required: "Topic name can't be blank."
                }
            }
        });
    });

</script>