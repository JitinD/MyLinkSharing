<%@ page contentType="text/html;charset=UTF-8" %>

Hello buddy, your friend has sent you an invitation to subscribe a new topic.
<g:link base = "${hostURL}" controller="topic" action="join" params="[id: topicId]">Click here to subscribe</g:link>