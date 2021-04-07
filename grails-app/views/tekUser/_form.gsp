<%@ page import="com.tekdays.TekUser" %>

<f:with bean="tekUserInstance">
    <f:field property="fullName"/>
    <f:field property="userName"/>
    <f:field property="password">
        <g:passwordField name="password" maxlength="15" required="" value="${tekUserInstance?.password}"/>
    </f:field>
    <f:field property="email"/>
    <f:field property="website"/>
    <f:field property="bio">
        <g:textArea name="bio" cols="40" rows="5" maxlength="5000" required="" value="${tekUserInstance?.bio}"/>
    </f:field>
</f:with>
