<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta name="layout" content="main">
        <title>Admin Title bar</title>
    </head>

    <body>
    <div class="panel panel-default panel-primary">
        <div class="panel-heading">
            <div class="row">
                <div class="col-xs-3">
                    Users
                </div>

                <div class="col-xs-offset-2 col-xs-7">
                    <g:form name="adminUsersSearchForm" class="form-inline" controller="user" action = "list">
                        <div class="form-group">
                            <div class="input-group">
                                %{--<g:select class = "btn btn-default" name="usersType" from = "${enums.UserStatus.values()}" optionKey = "key" />--}%
                                <select name = "isActive" id = "isActive" class = "btn btn-default">
                                    <option value = "${null}">All users</option>
                                    <option value = "${true}">Active users</option>
                                    <option value = "${false}">Inactive users</option>
                                </select>

                                &nbsp;&nbsp;&nbsp;

                                <span class="input-group-btn">
                                    <button class="btn btn-primary glyphicon glyphicon-search searchButtons" type = "submit"></button>
                                </span>

                                <span class = "input-group-btn">
                                    <input type="text" id = "q" name = "q" class="form-control input-group" placeholder="Search">
                                </span>

                                <span class="input-group-btn">
                                    <button class="btn btn-primary glyphicon-searchphicon glyphicon-remove searchButtons">
                                    </button>
                                </span>

                            </div>
                        </div>
                    </g:form>
                </div>
            </div>
        </div>

        <div class="panel-body">
            <div class="table-responsive">
                <table class="table table-condensed table-hover">
                    <thead>
                        <tr>
                            <g:sortableColumn property="id" title ="Id"/>
                            <g:sortableColumn property="userName" title ="Username"/>
                            <g:sortableColumn property="emailID" title ="Email"/>
                            <g:sortableColumn property="firstName" title ="First name"/>
                            <g:sortableColumn property="lastName" title ="Last name"/>
                            <g:sortableColumn property = "isActive" title ="Active"/>
                            <th>Manage</th>
                        </tr>
                    </thead>
                    <tbody>
                        <g:each in = "${usersList}" var = "user">
                            <g:if test="${user.isActive}">
                                <g:set var = "bootstrapClass" value = "alert alert-success" />
                            </g:if>
                            <g:else>
                                <g:set var = "bootstrapClass" value = "alert alert-danger" />
                            </g:else>

                            <tr class = "${bootstrapClass}">
                                <td>${user.userId}</td>
                                <td>${user.userName}</td>
                                <td>${user.emailID}</td>
                                <td>${user.firstName}</td>
                                <td>${user.lastName}</td>

                                <td>
                                    <g:if test = "${user.isActive}">
                                        Yes
                                    </g:if>

                                    <g:else>
                                        No
                                    </g:else>
                                </td>

                                <td>
                                    <g:link controller = "user" action = "toggleActive" params = "[id: user.userId]">
                                        <g:if test = "${user.isActive}">
                                            Deactivate
                                        </g:if>
                                        <g:else>
                                            Activate
                                        </g:else>
                                    </g:link>

                                </td>
                            </tr>
                        </g:each>
                    </tbody>

                </table>
            </div>
        </div>
    </div>

    </body>

</html>