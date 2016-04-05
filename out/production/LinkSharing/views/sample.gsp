<%--

<table border="1">
    <th>id </th>
    <th>FirstName </th>
    <th>LastName </th>

  <g:each in="${1..10}" >
        <tr>
            <td>${it}</td>
        </tr>
    </g:each>

</table>


--%>

<g:showAdmin isAdmin = 'true'>
    Is Admin. So you are able to see
</g:showAdmin>

<ls:showAdmin isAdmin = 'false'>
    Is User. So you are able to see
</ls:showAdmin>

<g:form action = "save">
    <g:renderErrors bean = "${user}"></g:renderErrors>
    Input <g:textField name="text" placeholder="Text"/>&nbsp;
    Password<g:passwordField name="password" placeholder= "Password"/><br/><br/>
    Options&nbsp;<g:select name="options" from="${[1,2,3,4]}" optionValue="${it}"></g:select><br/><br/>
    Male <g:radio name="gender" value="male"></g:radio>&nbsp;
    Female <g:radio name="gender" value="female"></g:radio><br/><br/>
    Cricket<g:checkBox name ="checkbox"></g:checkBox>
    Poker<g:checkBox name ="checkbox"></g:checkBox>
    Ludo<g:checkBox name ="checkbox"></g:checkBox>
    Run<g:checkBox name ="checkbox"></g:checkBox><br/><br/>
    <g:submitButton name="submit"></g:submitButton>
</g:form>