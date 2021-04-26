<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title><g:message code="default.login.label"/></title>
</head>

<body>
<g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
</g:if>
<g:form action="validate">
    <table>
        <tr class="prop">
            <td class="name">
                <label for="username"><g:message code="tekUser.userName.label"/>:</label>
            </td>
            <td class="value">
                <input type="text" id="username" name="username" value="">
            </td>
        </tr>
        <tr class="prop">
            <td class="name">
                <label for="password"><g:message code="tekUser.password.label"/>:</label>
            </td>
            <td class="value">
                <input type="password" id="password" name="password" value="">
                <input type="hidden" name="cName" value="${cName}">
                <input type="hidden" name="aName" value="${aName}">
                <input type="hidden" name="id" value="${id}">
            </td>
        </tr>
        <tr>
            <td>
            </td>
            <td>
                <input type="submit" value="<g:message code='default.login.label'/>"/>
            </td>
        </tr>
    </table>
</g:form>
</body>
</html>
