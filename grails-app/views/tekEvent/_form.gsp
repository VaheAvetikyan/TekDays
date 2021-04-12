<%@ page import="com.tekdays.TekEvent" %>

<f:with bean="tekEventInstance">
    <f:field property="name"/>
    <f:field property="nickname"/>
    <f:field property="city"/>
    <f:field property="venue"/>
    <f:field property="description">
        <g:textArea name="description" cols="40" rows="5" maxlength="5000" required=""
                    value="${tekEventInstance?.description}"/>
    </f:field>
    <f:field property="startDate">
        <g:datePicker name="startDate" precision="day" value="${tekEventInstance?.startDate}" years="${2021..2031}"/>
    </f:field>
    <f:field property="endDate">
        <g:datePicker name="endDate" precision="day" value="${tekEventInstance?.endDate}" years="${2021..2031}"/>
    </f:field>
    <f:field property="organizer">
        <g:if test="${session.user?.id == tekEventInstance?.organizer?.id}">
            <g:select id="organizer" name="organizer.id" from="${com.tekdays.TekUser.list()}" optionKey="id" required=""
                      value="${tekEventInstance?.organizer?.id}" class="many-to-one"/>
        </g:if>
        <g:else>
            <g:select id="organizer" name="organizer.id" from="${session.user}" optionKey="id" required=""
                      value="${tekEventInstance?.organizer?.id}" class="many-to-one"/>
        </g:else>
    </f:field>
    <f:field property="volunteers">
        <g:select name="volunteers" from="${com.tekdays.TekUser.list()}" multiple="multiple" optionKey="id" size="5"
                  value="${tekEventInstance?.volunteers*.id}" class="many-to-many"/>
    </f:field>
    <f:field property="sponsorships">
        <ul class="one-to-many">
            <g:each in="${tekEventInstance?.sponsorships ?}" var="s">
                <li><g:link controller="sponsorship" action="show"
                            id="${s.id}">${s?.sponsor?.encodeAsHTML()}</g:link></li>
            </g:each>
            <li class="add">
                <g:link controller="sponsorship" action="create"
                        params="['tekEvent.id': tekEventInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'sponsorship.label', default: 'Sponsorship')])}</g:link>
            </li>
        </ul>
    </f:field>
    <f:field property="tasks">
        <ul class="one-to-many">
            <g:each in="${tekEventInstance?.tasks ?}" var="t">
                <li><g:link controller="task" action="show" id="${t.id}">${t?.title?.encodeAsHTML()}</g:link></li>
            </g:each>
            <li class="add">
                <g:link controller="task" action="create"
                        params="['tekEvent.id': tekEventInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'task.label', default: 'Task')])}</g:link>
            </li>
        </ul>
    </f:field>
    <f:field property="messages">
        <ul class="one-to-many">
            <g:each in="${tekEventInstance?.messages ?}" var="m">
                <li><g:link controller="tekMessage" action="show"
                            id="${m.id}">${m?.subject?.encodeAsHTML()}</g:link></li>
            </g:each>
            <li class="add">
                <g:link controller="tekMessage" action="create" params="['event.id': tekEventInstance?.id]">
                    ${message(code: 'default.add.label', args: [message(code: 'tekMessage.label', default: 'TekMessage')])}
                </g:link>
            </li>
        </ul>
    </f:field>
</f:with>

<div class="fieldcontain ${hasErrors(bean: tekEventInstance, field: 'respondents', 'error')} ">
    <label for="respondents">
        <g:message code="tekEvent.respondents.label" default="Respondents"/>
    </label>
</div>
