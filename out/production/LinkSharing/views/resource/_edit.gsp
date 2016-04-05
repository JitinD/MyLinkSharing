<div class="modal fade" id="editDescriptionModal" tabindex="-1" role="dialog" aria-labelledby="editDescriptionModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header panelHeaders">
                <h4 class="modal-title panelHeadersText" id="editDescriptionModalLabel">
                    Post update
                </h4>
            </div>

            <g:form class="form" controller="resource" action="save">
                <div class="modal-body table-responsive">

                    <div class="form-group row">
                        <label class="form-control-label col-xs-4">Description*</label>

                        <div class="col-xs-6">
                            <g:textArea id="description" name="description" class="form-control"/>
                            <g:hiddenField name="id" id = "id" />
                        </div>
                    </div>


                    <div class="form-group row">
                        <div class="col-xs-offset-4 col-xs-4">
                            <g:submitButton class="btn btn-primary submitButtons" type="submit"
                                            name="submit" value="Update"/>
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