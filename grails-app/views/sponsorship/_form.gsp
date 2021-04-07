<%@ page import="com.tekdays.Sponsorship" %>

<f:with bean="sponsorshipInstance">
    <f:field property="event"/>
    <f:field property="sponsor"/>
    <f:field property="contributionType">
        <g:select name="contributionType" from="${sponsorshipInstance.constraints.contributionType.inList}" required=""
                  value="${sponsorshipInstance?.contributionType}"/>
    </f:field>
    <f:field property="description">
        <g:textField name="description" value="${sponsorshipInstance?.description}"/>
    </f:field>
    <f:field property="notes">
        <g:textArea name="notes" cols="40" rows="5" maxlength="5000" value="${sponsorshipInstance?.notes}"/>
    </f:field>
</f:with>
