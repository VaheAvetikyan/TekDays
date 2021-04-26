<span>
    <g:link controller="tekEvent" action="show" id="${event.id}">
        <h1>${event}</h1>
    </g:link>
</span>
<table>
    <tr>
        <td><g:message code="tekEvent.startDate.label"/>:
            <g:formatDate format="MMM/dd/yyyy" date="${event.startDate}"/></td>
        <td>
            <g:if test="${event.endDate}">
                <g:message code="tekEvent.endDate.label"/>: <g:formatDate format="MMM/dd/yyyy" date="${event.endDate}"/>
            </g:if>
        </td>
    </tr>
    <tr>
        <td><g:message code="tekEvent.venue.label"/>: ${event.venue}</td>
        <td><g:message code="tekEvent.respondents.label"/>: ${event.respondents.size()}</td>
    </tr>
</table>
