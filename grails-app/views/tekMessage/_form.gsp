<%@ page import="com.tekdays.TekMessage" %>

<g:if test="${tekMessageInstance?.parent}">
    <div class="fieldcontain ${hasErrors(bean: tekMessageInstance, field: 'parent', 'error')} ">
        <input type="hidden" name="parent.id" value="${tekMessageInstance?.parent?.id}"/>
        <label for="parent">
            In Reply to: ${tekMessageInstance?.parent?.author}
        </label>
    </div>
</g:if>

<div class="fieldcontain ${hasErrors(bean: tekMessageInstance, field: 'subject', 'error')} required">
    <label for="subject">
        <g:message code="tekMessage.subject.label" default="Subject"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="subject" class="messageField" required="" value="${tekMessageInstance?.subject}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tekMessageInstance, field: 'content', 'error')} required">
    <label for="content">
        <g:message code="tekMessage.content.label" default="Content"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textArea name="content" class="messageField" cols="40" rows="5"
                maxlength="2000" required="" value="${tekMessageInstance?.content}"/>

</div>

<g:hiddenField name="event.id" value="${tekMessageInstance?.event?.id}"/>
<g:hiddenField name="author.id" value="${session.user.id}"/>
