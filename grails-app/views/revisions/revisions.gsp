<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'revisions.label', default: 'Revisions')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
    <g:javascript library="jquery"/>
</head>

<body>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}">
            <g:message code="default.home.label"/></a></li>
        <li><g:link class="list" controller="${revisionList[0][0].class.simpleName}" action="index">
            <g:message code="default.list.label" args="[revisionList[0][0].class.simpleName]"/></g:link></li>
    </ul>
</div>

<div class="content scaffold-list" role="main">
    <g:form url="[resource: revisions, action: 'compare']" method="PUT">
        <g:hiddenField name="id" value="${revisionList[0][0].id}"/>
        <g:hiddenField name="type" value="${revisionList[0][0].class.name}"/>
        <g:showRevisions revisionList="${revisionList}" showList="${showList}"/>
        <fieldset class="buttons">
            <g:actionSubmit class="save" id="compare-diff" action="compare"
                            value="Compare Differences"/>
        </fieldset>
    </g:form>
</div>
</body>
<script type="text/javascript">
    $(document).ready(function () {
        $("#compare-diff").onclick(countOfSelectedRevisions);
    });

    function countOfSelectedRevisions() {
        let _count = $("input[name='revId']:checked").length;
        if (_count > 1) {
            return confirm("Count of selected revisions is " + _count + "\n\n${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}");
        } else {
            alert('Count of selected revisions should be more than 1!');
            return false;
        }
    }
</script>
</html>
