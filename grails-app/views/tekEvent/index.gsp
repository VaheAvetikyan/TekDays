<%@ page import="com.tekdays.TekEvent" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'tekEvent.label', default: 'TekEvent')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
    <g:javascript>
        $(document).ready(function () {
            $('#dt').DataTable({
                sScrollY: "75%",
                sScrollX: "100%",
                bProcessing: true,
                bServerSide: true,
                sAjaxSource: "${createLink(controller: entityName, action: 'dataTablesRenderer', params: [properties: properties])}",
                bJQueryUI: false,
                bAutoWidth: false,
                sPaginationType: "full_numbers",
                aLengthMenu: [[10, 25, 50, 100, 200], [10, 25, 50, 100, 200]],
                iDisplayLength: 10,
                bStateSave: true,
                aoColumnDefs: [
                    {
                        visible: false,
                        aTargets: [9]
                    },
                    {
                        bSearchable: false,
                        aTargets: [3, 4, 7, 9]
                    },
                    {
                        bSortable: false,
                        aTargets: [5, 7, 9]
                    },
                    {
                        aTargets: [0],
                        'visible': false
                    },
                    {
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
                        aTargets: [3, 4]
                    },
                    {
                         render: function (data, type, full, meta) {
                            if (full) {
                                return '<a href="${createLink(controller: 'tekUser', action: 'show')}/' + full[9] + '" class="btn">' + data + '</a>';
                            } else {
                                return data;
                            }
                        },
                        aTargets: [6]
                    },
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
                        createdCell: function (td, cellData, rowData, row, col) {
                            $(td).attr('style', 'text-align: center;');
                        },
                        render: function (data, type, full, meta) {
                            if (data) {
                                return '<a href="${createLink(action: 'edit')}/' + data + '" class="btn">Edit</a>';
                            } else {
                                return "";
                            }
                        },
                        aTargets: [7]
                    },
                    {
                        render: function (data, type, full, meta) {
                            if (data) {
                                return '<a href="${createLink(controller: 'revisions', action: 'revisions')}/' + data + '?type=com.tekdays.TekEvent" class="btn">Revisions</a>';
                            } else {
                                return "";
                            }
                        },
                        aTargets: [8]
                    }]
            });
        });
    </g:javascript>
</head>

<body>
<a href="#list-tekEvent" class="skip" tabindex="-1">
    <g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}">
            <g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create">
            <g:message code="default.new.label" args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="list-tekEvent" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table class="compact cell-border order-column hover" id="dt">
        <thead>
        <tr>
            <g:each in="${properties}" var="prop">
                <th>${prop.capitalize()}</th>
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
