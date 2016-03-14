<div class="panel-body">
    <div class="row">
        <div class="col-xs-3">
            <ls:userImage id="${topic.createdBy.id}"/>
        </div>

        <div class="col-xs-9">
            <div class="row">
                <div class="col-xs-8">
                    <g:link controller = "user" action = "profile" params = "[id: topic.createdBy.id]">${topic.createdBy.name}</g:link>
                </div>

                <div class="col-xs-4"><a href="/topic/show?id=${topic.id}">${topic.name}</a></div>
            </div>

            <div class = "row">
                <div id = "topicNameEditForm${topic.id}" class = "form-inline" style = "display: none;">
                    <div class = "input-group">
                        <g:textField id = "newTopicName${topic.id}" name="topicName" value = "${topic.name}"/>
                        <g:hiddenField id = "oldTopicName${topic.id}" name = "topicName" value = "${topic.name}" />
                        <button class = "saveTopicNameButton btn-primary" topicId = ${topic.id}>Save</button>
                        <button class = "closeTopicSaveForm btn-danger" topicId = ${topic.id}>Cancel</button>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-xs-4 text-muted"><small>(@${topic.createdBy.userName})</small></div>

                <div class="col-xs-4 text-muted">Subscriptions</div>

                <div class="col-xs-4 text-muted">Post</div>
            </div>

            <div class="row">
                <ls:showSubscribe id="${topic.id}"/>
                <div class="col-xs-4"><ls:subscriptionCount topicId="${topic.id}"/></div>

                <div class="col-xs-4"><ls:resourceCount topicId="${topic.id}"/></div>
            </div>
        </div>
    </div>
</div>

<g:if test="${session.user}">

    <div class="panel panel-footer">
        <div class="row">


            <div class="col-xs-4">
                <ls:showSeriousness id="${topic.id}"/>
            </div>

            <ls:canUpdateTopic id="${topic.id}">

                <div class="col-xs-3">
                    <ls:showVisibility id="${topic.id}"/>
                </div>

                <div class="col-xs-offset-1 col-xs-1">
                    <a href="" class = "editTopicNameButton" topicId = "${topic.id}">
                        <span class = "glyphicon glyphicon-edit panelIcons"></span>
                    </a>
                </div>


                <div class="col-xs-1">
                    <a href="/topic/delete/${topic.id}">
                        <span class = "glyphicon glyphicon-trash panelIcons"></span>
                    </a>
                </div>

            </ls:canUpdateTopic>

            <ls:canInviteToTopic id = "${topic.id}">

                <div class="col-xs-1">
                    <a href = "" class="btn invite" topicId = "${topic.id}" name = "invite" data-toggle="modal" data-target="#sendInviteModal">
                        <span class = "glyphicon glyphicon-envelope panelIcons"></span>
                    </a>
                </div>

            </ls:canInviteToTopic>

        </div>
    </div>

    <script>
        $(document).ready(function(){

           /*$(".invite").click(function(){

               var topicId = $(this).attr('topicId');
               $(".sendInviteModal #topic").val(topicId);
               $(".sendInviteModal #topic").prop('disabled', 'disabled');
           });
*/

            $(".editTopicNameButton").click(function (e) {
                e.preventDefault();
                topicId = $(this).attr('topicId');

                $("#topicNameEditForm"+topicId).css({'display': 'block'})
            });

            $(".closeTopicSaveForm").click(function(e){
                e.preventDefault();
                topicId = $(this).attr('topicId');

                $("#topicNameEditForm"+topicId).css({'display': 'none'})
            });

        });
    </script>

</g:if>