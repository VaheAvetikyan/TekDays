<%@ page import="com.tekdays.Task" %>

<f:with bean="taskInstance">
    <f:field property="title"/>
    <f:field property="notes">
        <g:textArea name="notes" cols="40" rows="5" maxlength="5000" value="${taskInstance?.notes}"/>
    </f:field>
    <f:field property="assignedTo"/>
    <f:field property="dueDate">
        <g:datePicker name="dueDate" precision="day" value="${taskInstance?.dueDate}" default="none"
                      noSelection="['': '']" years="${2021..2031}"/>
    </f:field>
    <f:field property="completed"/>
    <g:if test="${taskInstance?.event == null}">
        <f:field property="event"/>
    </g:if>
    <g:else>
        <g:hiddenField name="event.id" value="${taskInstance?.event?.id}" class="many-to-one"/>
    </g:else>
</f:with>
