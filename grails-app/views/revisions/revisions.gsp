<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'revisions.label', default: 'Revisions')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
    <g:javascript>
    function countOfSelectedRevisions() {
        let _count = $("input[name='revId']:checked").length;
        if (_count > 1) {
            return confirm("${message(code: "revisions.alert.confirm.message")} " + _count + "\n\n${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}");
        } else {
            alert('${message(code: "revisions.alert.message")}');
            return false;
        }
    }
    </g:javascript>
</head>

<body>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}">
            <g:message code="default.home.label"/></a></li>
        <li><g:link class="list" controller="${revisionList?.getAt(0)?.getAt(0)?.class?.simpleName}" action="index">
            <g:message code="default.list.label"
                       args="[revisionList?.getAt(0)?.getAt(0)?.class?.simpleName]"/></g:link></li>
    </ul>
</div>

<div class="content scaffold-list table table-responsive" role="main">
    <g:form url="[resource: revisions, action: 'compare']" method="PUT">
        <g:hiddenField name="id" value="${revisionList?.getAt(0)?.getAt(0)?.id}"/>
        <g:hiddenField name="type" value="${revisionList?.getAt(0)?.getAt(0)?.class?.name}"/>
        <g:showRevisions revisionList="${revisionList}" showList="${showList}"/>
        <g:if test="${revisionList}">
            <fieldset class="buttons">
                <g:actionSubmit class="save" id="compare-diff" action="compare"
                                onclick="return countOfSelectedRevisions();" value="${message(code: 'revisions.compare.button.label')}"/>
            </fieldset>
        </g:if>
    </g:form>
</div>
</body>
</html>
