<%@ page import="com.tekdays.Sponsor" %>

<f:with bean="sponsorInstance">
    <f:field property="name"/>
    <f:field property="website"/>
    <f:field property="description">
        <g:textArea name="description" cols="40" rows="5" maxlength="5000" value="${sponsorInstance?.description}"/>
    </f:field>
    <f:field property="logo"/>
    <f:field property="sponsorships">
        <ul class="one-to-many">
            <g:each in="${sponsorInstance?.sponsorships ?}" var="s">
                <li><g:link controller="sponsorship" action="show"
                            id="${s.id}">${s?.event?.encodeAsHTML()}</g:link></li>
            </g:each>
            <li class="add">
                <g:link controller="sponsorship" action="create" params="['sponsor.id': sponsorInstance?.id]">
                    ${message(code: 'default.add.label', args: [message(code: 'sponsorship.label', default: 'Sponsorship')])}
                </g:link>
            </li>
        </ul>
    </f:field>
</f:with>
