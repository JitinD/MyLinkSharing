<g:each in = "${topicPosts}" var = "post">
    <g:render template = "/resource/show" model = "[post : post]" />
</g:each>