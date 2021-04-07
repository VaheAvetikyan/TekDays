<%@ page import="com.tekdays.TekMessage" %>

<g:if test="${tekMessageInstance?.parent}">
    <div class="fieldcontain ${hasErrors(bean: tekMessageInstance, field: 'parent', 'error')} ">
        <input type="hidden" name="parent.id" value="${tekMessageInstance?.parent?.id}"/>
        <label for="parent">
            In Reply to: ${tekMessageInstance?.parent?.author}
        </label>
    </div>
</g:if>

<f:with bean="tekMessageInstance">
    <f:field property="subject"/>
    <f:field property="content">
        <g:textArea name="content" class="messageField" cols="40" rows="5"
                    maxlength="2000" required="" value="${tekMessageInstance?.content}"/>
    </f:field>
</f:with>
<g:hiddenField name="event.id" value="${tekMessageInstance?.event?.id}"/>
<g:hiddenField name="author.id" value="${session.user.id}"/>
