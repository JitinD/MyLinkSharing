<div class="modal fade" id="deleteResourceConfirmationModal" tabindex="-1" role="dialog" aria-labelledby="deleteResourceConfirmationModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header panelHeaders">
                <h4 class="modal-title panelHeadersText" id="deleteResourceConfirmationModalLabel">
                    Do you want to delete
                </h4>
            </div>
            <g:form class="form form-horizontal" controller = "resource" action = "delete">
                <div class="modal-body table-responsive">
                    <div class="form-group row form-inline">
                        <div class="col-xs-offset-4 col-xs-4">
                            <g:hiddenField name="id" id="id" />
                            <g:submitButton class="btn btn-primary submitButtons" type="submit"
                                            name="submit" value="Yes"/>
                        </div>

                        <div class="col-xs-4">
                            <button type="button" class="btn btn-primary submitButtons"
                                    data-dismiss="modal">No</button>
                        </div>
                    </div>
                </div>
            </g:form>
        </div>
    </div>
</div>
