<div class="modal fade" id="createTopicModal" tabindex="-1" role="dialog" aria-labelledby="createtopicModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header" style="background-color:#cfd7ff;">
                <h4 class="modal-title" id="createTopicModalLabel" style="color: #4ba2f7; font-weight: bold">
                    Create Topic
                </h4>
            </div>
            <g:form class="form form-horizontal">
                <div class="modal-body table-responsive">
                    <div class="form-group row">
                        <label class="form-control-label col-xs-4">Name*</label>

                        <div class="col-xs-6">
                            <g:textField id="topicName" name="topicName" class="form-control"/>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label name="visibility" id="visibility" class="form-control-label col-xs-4">
                            Visibility *
                        </label>

                        <div class="col-xs-8">
                            <div class="dropdown">
                                <g:select name="visibility" from="${enums.Visibility.values()}"
                                          class="btn btn-default btn-sm dropdown-toggle"/>
                            </div>
                        </div>
                    </div>

                    <div class="form-group row">
                        <div class="col-xs-offset-4 col-xs-4">
                            <g:submitButton class="btn btn-primary" formaction="/topic/save" type="submit" name="submit"
                                            value="Save" style="background-color:#4ba2f7;"/>
                        </div>

                        <div class="col-xs-4">
                            <button type="button" class="btn btn-primary" data-dismiss="modal"
                                    style="background-color:#4ba2f7;">Close</button>
                        </div>

                    </div>
                </div>
            </g:form>
        </div>
    </div>
</div>
