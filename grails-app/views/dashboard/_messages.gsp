<h3><g:message code="tekMessage.ForumMessages.label"/></h3>
<table>
    <thead>
    <tr>
        <th><g:message code="tekMessage.author.label"/></th>
        <th><g:message code="tekMessage.subject.label"/></th>
        <th><g:message code="tekMessage.content.label"/></th>
    </tr>
    </thead>
    <g:each in="${messages}" var="msg">
        <tr>
            <td>
                <g:link controller="tekUser" action="show" id="${msg.author.id}">
                    ${msg.author}
                </g:link>
            </td>
            <td>
                ${msg.subject}
            </td>
            <td>
                ${msg.content[0..Math.min(msg.content.size() - 1, 24)]}
                ${msg.content.size() > 25 ? '...' : ''}
            </td>
        </tr>
    </g:each>
</table>
<g:link controller="tekMessage" action="index" id="${event.id}">
    <p><g:message code="tekMessage.View-threaded-messages.label"/></p>
</g:link>
