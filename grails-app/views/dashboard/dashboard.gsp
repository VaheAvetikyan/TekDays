<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>TekDays - Dashboard</title>
    <meta name="layout" content="main"/>
</head>

<body>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}">Home</a></li>
        <li><g:link class="create" controller="task" action="create">Create Task</g:link></li>
        <li><g:link class="create" controller="sponsorship" action="create">Add Sponsor</g:link></li>
        <li><g:link class="list" controller="sponsor" action="index">All Sponsors</g:link></li>
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
