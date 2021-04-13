<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'revisions.label', default: 'Select Revisions')}"/>
    <title><g:message code="default.add.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}">
            <g:message code="default.home.label"/></a></li>
        <li><g:link class="list" controller="${instance.getClass().simpleName}" action="index">
            <g:message code="default.list.label" args="[instance.getClass().simpleName]"/></g:link></li>
    </ul>
</div>

<g:form url="[resource: revisions, action: 'revisions']" method="PUT">
    <g:hiddenField name="id" value="${instance.id}"/>
    <g:hiddenField name="type" value="${instance.getClass().name}"/>
    <fieldset class="form">
        <label for="showList"><h1>Select Fields to Show Revisions for </h1></label>
        <g:select name="showList" from="${instance.properties.keySet()}" multiple="multiple"
                  size="${instance.properties.size()}"
                  required="" value="${showList}"/>
    </fieldset>
    <fieldset class="buttons">
        <g:actionSubmit class="save" action="revisions"
                        value="Show Revisions"/>
    </fieldset>
</g:form>
</body>
</html>
