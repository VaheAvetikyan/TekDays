<%@ page import="com.tekdays.Task" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'task.label', default: 'Task')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-task" class="skip" tabindex="-1">
    <g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}">
            <g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create">
            <g:message code="default.new.label" args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="list-task" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            <g:sortableColumn property="title" title="${message(code: 'task.title.label', default: 'Title')}"/>

            <g:sortableColumn property="notes" title="${message(code: 'task.notes.label', default: 'Notes')}"/>

            <th><g:message code="task.assignedTo.label" default="Assigned To"/></th>

            <g:sortableColumn property="dueDate" title="${message(code: 'task.dueDate.label', default: 'Due Date')}"/>

            <g:sortableColumn property="completed"
                              title="${message(code: 'task.completed.label', default: 'Completed')}"/>

            <th><g:message code="task.event.label" default="Event"/></th>

            <th>Get Rev</th>

        </tr>
        </thead>
        <tbody>
        <g:each in="${taskInstanceList}" status="i" var="taskInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show"
                            id="${taskInstance.id}">${fieldValue(bean: taskInstance, field: "title")}</g:link></td>

                <td>${fieldValue(bean: taskInstance, field: "notes")}</td>

                <td>${fieldValue(bean: taskInstance, field: "assignedTo")}</td>

                <td><g:formatDate date="${taskInstance.dueDate}"/></td>

                <td><g:formatBoolean boolean="${taskInstance.completed}"/></td>

                <td>${fieldValue(bean: taskInstance, field: "event")}</td>

                <td><g:link action="revisions" id="${taskInstance?.id}">${taskInstance?.id}</g:link></td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${taskInstanceCount ?: 0}"/>
    </div>
</div>
</body>
</html>
