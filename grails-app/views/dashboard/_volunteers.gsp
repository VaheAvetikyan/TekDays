<h3><g:message code="tekEvent.volunteers.label"/></h3>
<table>
    <thead>
    <tr>
        <th><g:message code="tekUser.fullName.label"/></th>
        <th><g:message code="tekUser.email.label"/></th>
        <th><g:message code="tekUser.website.label"/></th>
    </tr>
    </thead>
    <g:each in="${volunteers}" var="volunteer">
        <tr>
            <td>
                <g:link controller="tekUser" action="show" id="${volunteer.id}">
                    ${volunteer.fullName}
                </g:link>
            </td>
            <td>
                <a href="mailto:${volunteer.email}">
                    ${volunteer.email}

                </a>
            </td>
            <td>
                <a href="${volunteer.website}">
                    ${volunteer.website}
                </a>
            </td>
        </tr>
    </g:each>
</table>
