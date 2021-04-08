<%@ page import="com.tekdays.UserRevisionEntity" contentType="text/html;charset=UTF-8" %>
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
        <th>ChangedDate</th>
        <th>User</th>
    </tr>
    </thead>
    <g:each in="${revisionList}" var="rev" status="i">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
            <td>${rev[1]?.id}</td>
            <td>${rev[2]}</td>
            <td>${rev[0]?.version}</td>
            <td>${rev[0]?.title}</td>
            <td>${rev[0]?.completed}</td>
            <td>${rev[0]?.dueDate}</td>
            <td>${rev[0]?.notes}</td>
            <td>${UserRevisionEntity.read(rev[1]?.id)?.revisionDate?.format('yyyy-MM-dd HH:mm')}</td>
            <td>${UserRevisionEntity.read(rev[1]?.id)?.currentUser}</td>
        </tr>
    </g:each>
</table>
</body>
</html>