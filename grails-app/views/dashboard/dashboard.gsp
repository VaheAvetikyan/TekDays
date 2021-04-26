<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>TekDays - <g:message code="tekEvent.Dashboard.label"/></title>
    <meta name="layout" content="main"/>
</head>

<body>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}">Home</a></li>
        <li><g:link class="create" controller="task" action="create">
            <g:message code="default.create.label" args="[message(code: 'task.label')]"/></g:link></li>
        <li><g:link class="create" controller="sponsorship" action="create">
            <g:message code="default.add.label" args="[message(code: 'sponsor.label')]"/></g:link></li>
        <li><g:link class="list" controller="sponsor" action="index">
            <g:message code="default.list.label" args="[message(code: 'sponsor.label')]"/></g:link></li>
    </ul>
</div>

<div id="event" class="dashboard">
    <g:render template="event" model="${['event': event]}"/>
</div>

<div id="tasks" class="dashboard">
    <g:render template="tasks" model="${['tasks': tasks]}"/>
</div>

<div id="volunteers" class="dashboard">
    <g:render template="volunteers" model="${['volunteers': volunteers]}"/>
</div>

<div id="messages" class="dashboard">
    <g:render template="messages" model="${[messages: messages]}"/>
</div>

<div id="sponsors" class="dashboard">
    <g:render template="sponsors" model="${[sponsorships: sponsorships]}"/>
</div>
</body>
</html>
