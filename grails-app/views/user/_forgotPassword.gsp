<div class="modal fade" id="forgotPasswordModal" tabindex="-1" role="dialog" aria-labelledby="forgotPasswordModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header panelHeaders">
                <h4 class="modal-title panelHeadersText" id="forgotPasswordModalLabel">
                    Forgot Password
                </h4>
            </div>
            <g:form class="form form-horizontal">
                <div class="modal-body table-responsive">
                    <div class="form-group row">
                        <label class="form-control-label col-xs-4">Registered Email ID</label>

                        <div class="col-xs-6">
                            <g:field type = "email" id="emailID" name="emailID" class="form-control"/>
                        </div>
                    </div>

                    <div class="form-group row">
                        <div class="col-xs-offset-4 col-xs-4">
                            <g:submitButton class="btn btn-primary submitButtons" type="submit"
                                            name="submit" value="Submit"/>
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