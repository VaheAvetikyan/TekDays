<%@ page import="com.tekdays.Task" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'task.label', default: 'Task')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
    <g:javascript>
        $(document).ready(function () {
            $('#dt').DataTable({
                sScrollY: "75%",
                sScrollX: "100%",
                bProcessing: true,
                bServerSide: true,
                sAjaxSource: "${createLink(controller: 'Task', action: 'dataTablesRenderer', params: [properties: properties])}",
                bJQueryUI: false,
                bAutoWidth: false,
                sPaginationType: "full_numbers",
                aLengthMenu: [[10, 25, 50, 100, 200], [10, 25, 50, 100, 200]],
                iDisplayLength: 10,
                bStateSave: true,
                aoColumnDefs: [
                    {
                         render: function (data, type, full, meta) {
                            if (full) {
                                return '<a href="${createLink(action: 'show')}/' + full[7] + '" class="btn">' + data + '</a>';
                            } else {
                                return data;
                            }
                        },
                        aTargets: [0]
                    },
                    {
                         render: function (data, type, full, meta) {
                            if (full[8]) {
                                return '<a href="${createLink(controller: 'tekUser', action: 'show')}/' + full[8] + '" class="btn">' + data + '</a>';
                            } else {
                                return data;
                            }
                        },
                        aTargets: [1]
                    },
                    {
                        bSearchable: false,
                        render: function (data) {
                            if (data) {
                                let d = new Date(data);
                                let ye = new Intl.DateTimeFormat('en', { year: 'numeric' }).format(d);
                                let mo = new Intl.DateTimeFormat('en', { month: 'short' }).format(d);
                                let da = new Intl.DateTimeFormat('en', { day: '2-digit' }).format(d);
                                let date = da + '-' + mo + '-' + ye
                                return date;
                            } else {
                                return "";
                            }
                        },
                        aTargets: [2]
                    },
                    {
                         render: function (data, type, full, meta) {
                            if (full[9]) {
                                return '<a href="${createLink(controller: 'tekEvent', action: 'show')}/' + full[9] + '" class="btn">' + data + '</a>';
                            } else {
                                return data;
                            }
                        },
                        aTargets: [5]
                    },
                     {
                         bSearchable: false,
                         bSortable: false,
                        createdCell: function (td, cellData, rowData, row, col) {
                            $(td).attr('style', 'text-align: center;');
                        },
                        render: function (data, type, full, meta) {
                            if (data) {
                                return '<a href="${createLink(action: 'edit')}/' + data + '" class="btn">${message(code: "default.button.edit.label")}</a>';
                            } else {
                                return "";
                            }
                        },
                        aTargets: [6]
                    },
                    {
                        bSearchable: false,
                        bSortable: false,
                        render: function (data, type, full, meta) {
                            if (data) {
                                return '<a href="${createLink(controller: 'revisions', action: 'revisions')}/' + data + '?type=com.tekdays.Task" class="btn">${message(code: "revisions.label")}</a>';
                            } else {
                                return "";
                            }
                        },
                        aTargets: [7]
                    },
                    {
                        visible: false,
                        bSearchable: false,
                        aTargets: [8, 9]
                    }]
            });
        });
    </g:javascript>
</head>

<body>
<a href="#list-task" class="skip" tabindex="-1">
    <g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}">
            <g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create">
            <g:message code="default.new.label" args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="list-task" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <div class="page-body">
        <g:jasperCustom entityName="Task"/>
    </div>

    <div class="page-body">
        <g:jasperMail/>
    </div>

    <table class="compact cell-border hover" id="dt">
        <thead>
        <tr>
            <g:each in="${properties}" var="prop">
                <th>${prop}</th>
            </g:each>
        </tr>
        </thead>
        <tbody></tbody>
        <tfoot>
        <tr>
            <g:each in="${properties}" var="prop">
                <th>${prop.capitalize()}</th>
            </g:each>
        </tr>
        </tfoot>
    </table>
</div>
</body>
</html>
