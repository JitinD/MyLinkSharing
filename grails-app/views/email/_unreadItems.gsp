Hello ${user.name}<br/>,

You have following unread items:<br>
    <table cellpadding = "5px" cellspacing = "5px">
    <tr>
        <th>S.No.</th>
        <th>From Topic</th>
    </tr>

    <g:each var="resource" in="${unreadResources}" status="count">
        <tr>
            <td>${count}</td>
            <td>${resource.topic}</td>
        </tr>
    </g:each>

    </table>