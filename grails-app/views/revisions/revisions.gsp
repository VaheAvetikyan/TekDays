<%@ page import="com.tekdays.TekEvent" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'revisions.label', default: 'Revisions')}"/>
    <title>Revisions</title>
</head>

<body>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}">
            <g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="index">
            <g:message code="default.list.label" args="[entityName]"/></g:link></li>
    </ul>
</div>

<div class="content scaffold-list" role="main">
    <g:showRevisions revisionList="${revisionList}" showList="${showList}"/>
</div>
</body>
</html>