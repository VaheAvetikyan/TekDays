<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'book.label', default: 'Book')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
    <g:javascript>
        $(document).ready(function () {
            $('#dt').DataTable({
                sScrollY: "75%",
                sScrollX: "100%",
                bProcessing: true,
                bServerSide: true,
                sAjaxSource: "/TekDays/tekEvent/dataTablesRenderer",
                bJQueryUI: false,
                bAutoWidth: false,
                sPaginationType: "full_numbers",
                aLengthMenu: [[10, 25, 50, 100, 200], [10, 25, 50, 100, 200]],
                iDisplayLength: 10,
                bStateSave: true,
                aoColumnDefs: [
                    {
                        bSearchable: false,
                        aTargets: [0]
                    },
                    {
                        bSortable: false,
                        aTargets: [1]
                    },
                    {
                        createdCell: function (td, cellData, rowData, row, col) {
                            $(td).attr('style', 'text-align: center;');
                        },
                        render: function (data, type, full, meta) {
                            if (data) {
                                return '<a href="edit/' + data + '" class="btn">Edit</a>';
                            } else {
                                return "";
                            }
                        },
                        aTargets: [2]
                }]
            });
        });
    </g:javascript>
</head>

<body>
<table class="display compact" id="dt">
    <thead>
    <tr>
        <th>Name</th>
        <th>City</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody></tbody>
    <tfoot>
    <tr>
        <th>Name</th>
        <th>City</th>
        <th>Action</th>
    </tr>
    </tfoot>
</table>
</body>
</html>
