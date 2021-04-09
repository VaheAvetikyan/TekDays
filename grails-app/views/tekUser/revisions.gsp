<%@ page import="com.tekdays.UserRevisionEntity" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'tekUser.label', default: 'TekUser')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
%{--<table>--}%
%{--    <thead>--}%
%{--    <tr>--}%
%{--        <th>RevId</th>--}%
%{--        <th>RevType</th>--}%
%{--        <th>Version</th>--}%
%{--        <th>FullName</th>--}%
%{--        <th>userName</th>--}%
%{--        <th>password</th>--}%
%{--        <th>email</th>--}%
%{--        <th>website</th>--}%
%{--        <th>bio</th>--}%
%{--        <th>ChangedDate</th>--}%
%{--        <th>User</th>--}%
%{--    </tr>--}%
%{--    </thead>--}%
%{--    <tbody>--}%
%{--    <g:each in="${revisionList}" var="rev" status="i">--}%
%{--        <tr class=${(i % 2) == 0 ? 'even' : 'odd'}>--}%
%{--            <td>${rev[1].id}</td>--}%
%{--            <td>${rev[2]}</td>--}%
%{--            <td>${rev[1].version}</td>--}%
%{--            <td>${rev[0].fullName}</td>--}%
%{--            <td>${rev[0].userName}</td>--}%
%{--            <td>${rev[0].password}</td>--}%
%{--            <td>${rev[0].email}</td>--}%
%{--            <td>${rev[0].website}</td>--}%
%{--            <td>${rev[0].bio}</td>--}%
%{--            <td>${UserRevisionEntity.read(rev[1]?.id)?.revisionDate?.format('yyyy-MM-dd HH:mm')}</td>--}%
%{--            <td>${UserRevisionEntity.read(rev[1]?.id)?.currentUser}</td>--}%
%{--        </tr>--}%
%{--    </g:each>--}%
%{--    </tbody>--}%
%{--</table>--}%
<g:showRevisions revisionList="${revisionList}"/>
</body>
</html>