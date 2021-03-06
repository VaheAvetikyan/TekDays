<%@ page import="com.tekdays.Sponsor" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'sponsor.label', default: 'Sponsor')}"/>
    <g:javascript library="jquery"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-sponsor" class="skip" tabindex="-1">
    <g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}">
            <g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create">
            <g:message code="default.new.label" args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="list-sponsor" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <div class="page-body">
        <g:jasperCustom entityName="Sponsor"/>
    </div>

    <div class="page-body">
        <g:jasperMail/>
    </div>

    <table>
        <thead>
        <tr>
            <g:sortableColumn property="name" title="${message(code: 'sponsor.name.label', default: 'Name')}"/>
            <g:sortableColumn property="website" title="${message(code: 'sponsor.website.label', default: 'Website')}"/>
            <g:sortableColumn property="description"
                              title="${message(code: 'sponsor.description.label', default: 'Description')}"/>
            <g:sortableColumn property="logo" title="${message(code: 'sponsor.logo.label', default: 'Logo')}"/>
            <th><g:message code="revisions.get.label"/></th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${sponsorInstanceList}" status="i" var="sponsorInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td><g:link action="show"
                            id="${sponsorInstance.id}">${fieldValue(bean: sponsorInstance, field: "name")}</g:link></td>
                <td>${fieldValue(bean: sponsorInstance, field: "website")}</td>
                <td>${fieldValue(bean: sponsorInstance, field: "description")}</td>
                <g:if test="${sponsorInstance?.logo}">
                    <td><img class="logo-image"
                             src="${createLink(controller: 'sponsor', action: 'fetchSponsorImage', params: ['name': sponsorInstance?.name])}"/>
                    </td>
                </g:if>
                <g:else>
                    <td></td>
                </g:else>
                <td><g:link controller="revisions" action="revisionSelect"
                            params="[type: sponsorInstance.getClass().name]"
                            id="${sponsorInstance?.id}">${sponsorInstance?.id}</g:link></td>
            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${sponsorInstanceCount ?: 0}"/>
    </div>
</div>
</body>
</html>
