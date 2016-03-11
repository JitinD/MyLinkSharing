<g:each in="${topics}" var="topic">
    <g:render template="/topic/show" model="[topic: topic]"/>
</g:each>
