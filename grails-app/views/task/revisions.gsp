<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'task.label', default: 'Task')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<table>
    <thead>
    <tr>
        <th>RevId</th>
        <th>RevType</th>
        <th>Version</th>
        <th>Title</th>
        <th>Competed</th>
        <th>DueDate</th>
        <th>Notes</th>
        <th>AssignedToId</th>
        <th>ChangedDate</th>
        <th>User</th>
    </tr>
    </thead>
    <g:each in="${revisionList}" var="rev">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
            <td>"${rev.id}"</td>
            <td>"${rev.revType}"</td>
            <td>"${rev.version}"</td>
            <td>"${rev.Title}"</td>
            <td>"${rev.completed}"</td>
            <td>"${rev.dueDate}"</td>
            <td>"${rev.notes}"</td>
            <td>"${rev.assignedTo}"</td>
            <td>"${rev?.revisionEntity?.revisionDate}"</td>
            <td>"${rev?.revisionEntity?.currentUser}"</td>
        </tr>
    </g:each>
</table>
</body>
</html>