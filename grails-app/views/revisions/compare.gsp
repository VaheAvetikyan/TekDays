<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'revisions.diff.label', default: 'Differences')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav" role="navigation">
    <ul>
        <li class="nav-item"><a class="home" href="${createLink(uri: '/')}">
            <g:message code="default.home.label"/></a></li>
        <li class="nav-item">
            <g:link controller="${revisionList?.getAt(0)?.getAt(0)?.class?.simpleName}" class="list">
                ${revisionList?.getAt(0)?.getAt(0)?.class?.simpleName} List
            </g:link>
        </li>
    </ul>
</div>

<div class="content">
    <div class="table table-bordered table-striped table-responsive table-hover">
        <g:showRevisions revisionList="${revisionList}" dif="yes"/>
    </div>
</div>
</body>
</html>
