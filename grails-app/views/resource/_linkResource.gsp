<div class="modal fade" id="shareLinkModal" tabindex="-1" role="dialog" aria-labelledby="sharelinkModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header panelHeaders">
                <h4 class="modal-title panelHeadersText" id="sharelinkModalLabel">
                    Share Link
                </h4>
            </div>

            <g:form class="form" controller="linkResource" action="save">
                <div class="modal-body table-responsive">

                    <div class="form-group row">
                        <label class="form-control-label col-xs-4">Link*</label>

                        <div class="col-xs-6">
                            <input type="url" id="url" name="url" class="form-control" required />
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
            </g:form>
        </div>
    </div>
</div>


<script>
    $(function () {
        $('#shareLinkModal').validate({
            rules: {
                'url': {
                    required: true
                }
            },
            messages: {
                'url': {
                    required: "Topic name can't be blank."
                }
            }
        });
    });

</script>