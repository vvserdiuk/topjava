/**
 * Created by vvserdiuk on 02.02.2016.
 */
var ajaxUrl = 'ajax/profile/meals/';
var datatableApi;

function updateTable() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + 'filter',
        data: $('#filter').serialize(),
        success: function (data) {
            updateTableByData(data);
        }
    });
    return false;
}

$(function () {
    datatableApi = $('#datatable').DataTable({
        "sAjaxSource": ajaxUrl,
        "sAjaxDataProp": "",
        "bPaginate": false,
        "bInfo": false,
        "aoColumns": [
            {
                "mData": "dateTime",
                "mRender": function (date, type, row) {
                    if (type == 'display') {
                        var dateObject = new Date(date);
                        return '<span>' + date.replace("T", " ") + '</span>';
                    }
                    return date;
                }
            },
            {
                "mData": "description"
            },
            {
                "mData": "calories"
            },
            {
                "sDefaultContent": "Edit",
                "bSortable": false,
                "mRender": renderEditBtn
            },
            {
                "sDefaultContent": "Delete",
                "bSortable": false,
                "mRender": renderDeleteBtn
            }
        ],
        "aaSorting": [
            [
                0,
                "desc"
            ]
        ],
        "createdRow": function (row, data, dataIndex) {
            if (data.exceed) {
                $(row).css("color", "red");
            }
        },
        "initComplete": makeEditable
    });

    $('#filter').submit(function () {
        updateTable();
        return false;
    });
    //makeEditable();
    //init();
});

//function init() {
//}