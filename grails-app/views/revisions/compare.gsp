<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <g:set var="entityName" value="${message(code: 'revisions.label', default: 'Differences')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>

<body>
<div class="container jumbotron">
    <div class="navbar">
        <ul class="nav">
            <li class="nav-item"><h2><a class="nav-link" href="${createLink(uri: '/')}">
                <g:message code="default.home.label"/></a></h2></li>
            <li class="nav-item"><h2>
                <g:link controller="${revisionList[0][0].class.simpleName}" class="nav-link">
                    ${revisionList[0][0].class.simpleName} List
                </g:link></h2>
            </li>
        </ul>
    </div>
</div>

<div class="container-fluid">
    <div class="table table-bordered table-striped table-dark table-hover">
        <g:showRevisions revisionList="${revisionList}" dif="true"/>
    </div>
</div>
</body>
</html>
