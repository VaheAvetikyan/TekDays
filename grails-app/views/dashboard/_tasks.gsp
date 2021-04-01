<h3>Things to do</h3>
<table>
    <thead>
    <tr>
        <th>Task Title</th>
        <th>Due Date</th>
        <th>Assigned To</th>
    </tr>
    </thead>
    <g:each in="${tasks}" var="task">
        <tr>
            <td>${task.title}</td>
            <td>
                <g:formatDate format="MM/dd/yyyy" date="${task.dueDate}"/>
            </td>
            <td>
                ${task.assignedTo}
            </td>
        </tr>
    </g:each>
</table>

<g:if test="${event.tasks.size() > 0}">
    <g:link controller="task" action="index" id="${event.id}">
        <p>View all ${event.tasks.size()} tasks for this event.</p>
    </g:link>
</g:if>
<g:else>
    <p>No tasks for this event</p>
</g:else>
