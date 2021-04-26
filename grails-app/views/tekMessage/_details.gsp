<div id="show-tekMessage" class="content scaffold-show" role="main">
    <br>

    <h3><g:message code="tekMessage.details.label"/></h3>
    <ol class="property-list tekMessage">
        <g:if test="${tekMessageInstance?.parent}">
            <li class="fieldcontain">
                <span class="property-label"><g:message code="tekMessage.in-reply-to.label"/></span>
                <span class="property-value" aria-labelledby="subject-label">
                    <g:fieldValue bean="${tekMessageInstance?.parent}" field="author"/></span>
            </li>
        </g:if>
        <g:if test="${tekMessageInstance?.subject}">
            <li class="fieldcontain">
                <span id="subject-label" class="property-label">
                    <g:message code="tekMessage.subject.label" default="Subject"/></span>
                <span class="property-value" aria-labelledby="subject-label">
                    <g:fieldValue bean="${tekMessageInstance}" field="subject"/></span>
            </li>
        </g:if>
        <g:if test="${tekMessageInstance?.content}">
            <li class="fieldcontain">
                <span id="content-label" class="property-label">
                    <g:message code="tekMessage.content.label" default="Content"/></span>
                <span class="property-value" aria-labelledby="content-label">
                    <g:fieldValue bean="${tekMessageInstance}" field="content"/></span>
            </li>
        </g:if>
        <g:if test="${tekMessageInstance?.author}">
            <li class="fieldcontain">
                <span id="author-label" class="property-label">
                    <g:message code="tekMessage.author.label" default="Author"/></span>
                <span class="property-value" aria-labelledby="author-label">
                    <g:link controller="tekUser" action="show"
                            id="${tekMessageInstance?.author?.id}">
                        ${tekMessageInstance?.author?.encodeAsHTML()}
                    </g:link></span>
            </li>
        </g:if>
    </ol>
    <g:form>
        <fieldset class="buttons">
            <g:link class="create" action="reply"
                    resource="${tekMessageInstance}"><g:message code="tekMessage.reply.label"/></g:link>
        </fieldset>
    </g:form>
</div>
