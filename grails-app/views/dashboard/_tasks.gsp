<h3><g:message code="tekEvent.tasks.label"/></h3>
<table>
    <thead>
    <tr>
        <th><g:message code="task.title.label"/></th>
        <th><g:message code="task.dueDate.label"/></th>
        <th><g:message code="task.assignedTo.label"/></th>
    </tr>
    </thead>
    <g:each in="${tasks}" var="task">
        <tr>
            <td><g:link controller="task" action="show" id="${task.id}">${task.title}</g:link></td>
            <td><g:formatDate format="MM/dd/yyyy" date="${task.dueDate}"/></td>
            <td><g:link controller="tekUser" action="show" id="${task.assignedToId}">${task.assignedTo}</g:link></td>
        </tr>
    </g:each>
</table>

<g:if test="${event.tasks.size() > 0}">
    <g:link controller="task" action="index" id="${event.id}">
        <p><g:message code="task.view-all.label" args="${event.tasks.size()}"/></p>
    </g:link>
</g:if>
<g:else>
    <p><g:message code="task.No-tasks.label"/></p>
</g:else>
