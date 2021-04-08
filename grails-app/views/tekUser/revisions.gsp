<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'tekUser.label', default: 'TekUser')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<table>
    <thead>
    <tr>
        <th>RevId</th>
        <th>RevType</th>
        <th>Version</th>
        <th>FullName</th>
    </tr>
    </thead>
    <tbody>
    <g:each in="${revisionList}" var="rev">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
            <td>"${rev.id}"</td>
            <td>"${rev.revType}"</td>
            <td>"${rev.version}"</td>
            <td>"${rev.fullName}"</td>
        </tr>
    </g:each>
    </tbody>
</table>
</body>
</html>