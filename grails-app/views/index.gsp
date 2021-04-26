<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="default.homepage.title"/></title>
</head>

<body>
<g:if test="${!session.user}">
    <div id="welcome" class="page-body">
        <h1><g:message code="default.homepage.welcome"/></h1>

        <p><g:message code="default.homepage.welcome.message"/></p>
    </div>
</g:if>
<g:organizerEvents/>
<g:volunteerEvents/>
<div class="homeCell">
    <h1><g:message code="default.homepage.find"/></h1>

    <p><g:message code="default.homepage.find.message"/></p>
    <span class="buttons">
        <g:link controller="tekEvent" action="index"><g:message code="default.homepage.find"/></g:link>
    </span>
</div>

<div class="homeCell">
    <h1><g:message code="default.homepage.organize"/></h1>

    <p><g:message code="default.homepage.organize.message"/></p>
    <span class="buttons">
        <g:link controller="tekEvent" action="create"><g:message code="default.homepage.organize"/></g:link>
    </span>
</div>

<div class="homeCell">
    <h1><g:message code="default.homepage.sponsor"/></h1>

    <p><g:message code="default.homepage.sponsor.message"/></p>
    <span class="buttons">
        <g:link controller="sponsor" action="create"><g:message code="default.homepage.sponsor"/></g:link>
    </span>
</div>
</body>
</html>
