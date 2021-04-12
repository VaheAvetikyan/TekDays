<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'tekEvent.label', default: 'TekEvent')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<g:form url="[resource: tekEventInstance, action: 'revisions']" method="PUT">
    <g:hiddenField name="id" value="${instance.id}"/>
    <fieldset class="form">
        <label for="showList">Select Fields to Show Revisions of</label>
        <g:select name="showList" from="${instance.properties.keySet()}" multiple="multiple" size="10" required=""
                  value="${showList}"/>
    </fieldset>
    <fieldset class="buttons">
        <g:actionSubmit class="save" action="revisions"
                        value="Show Revisions"/>
    </fieldset>
</g:form>
</body>
</html>
