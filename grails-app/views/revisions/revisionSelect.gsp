<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'revisions.label', default: 'Select Revisions')}"/>
    <title>Select Revisions</title>
</head>

<body>
<g:form url="[resource: revisions, action: 'revisions']" method="PUT">
    <g:hiddenField name="id" value="${instance.id}"/>
    <g:hiddenField name="type" value="${instance.getClass().name}"/>
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
