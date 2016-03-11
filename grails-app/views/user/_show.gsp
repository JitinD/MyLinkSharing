<div class="row">
    <div class="col-xs-3">
        <ls:userImage id = "${user.userId}" />
        %{--<img src="" class="img img-thumbnail img-responsive image" />--}%
    </div>

    <div class="col-xs-9">
        <div class="row">
            <div class="col-xs-12 text-primary">
                <g:link controller = "user" action = "profile">${user.name}</g:link>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-12 text-primary">
                <small>(@${user.name})</small>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-6 text-muted">Subscriptions</div>

            <div class="col-xs-6 text-muted">Topics</div>
        </div>

        <div class="row">
            <div class="col-xs-6 text-primary"><ls:subscriptionCount userId="${user.userId}"/></div>

            <div class="col-xs-6 text-primary"><ls:topicCount userId="${user.userId}"/></div>
        </div>

    </div>
</div>