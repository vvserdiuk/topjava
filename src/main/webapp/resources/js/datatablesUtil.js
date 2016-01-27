function makeEditable() {

    $('#add').click(function () {
        $('#id').val(0);
        $('#editRow').modal();
    });

    $('.delete').click(function () {
        deleteRow($(this).attr("id"));
    });

    $('#detailsForm').submit(function () {
        save();
        return false;
    });

    $('#filterForm').submit(function(){
        filter();
        return false;
    });

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(event, jqXHR, options, jsExc);
    });
}

function deleteRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        success: function () {
            updateTable();
            successNoty('Deleted');
        }
    });
}

//function enable(chkbox, id){
//    var enabled = chkbox.is(":checked");
//    //var id = $(this).attr('id');
//    //var enabled = $(this).is(":checked");
//    $.ajax({
//        url : ajaxUrl + id,
//        type: 'POST',
//        data: 'enabled=' + enabled,
//        success: function () {
//            successNoty(enabled ? 'Enabled' : 'Disabled');
//        }
//    })
//}

function enable(chkbox, id) {
    var enabled = chkbox.is(":checked");
    chkbox.closest('tr').css("text-decoration", enabled ? "none" : "line-through");
    $.ajax({
        url: ajaxUrl + id,
        type: 'POST',
        data: 'enabled=' + enabled,
        success: function () {
            successNoty(enabled ? 'Enabled' : 'Disabled');
        }
    });
}

function filter(){
    var form = $('#filterForm');
    $.ajax({
        type: 'GET',
        url: ajaxUrl + '/filter',
        data: form.serialize(),
        success: function(data){
            oTable_datatable.fnClearTable();
            $.each(data, function (key, item) {
                oTable_datatable.fnAddData(item);
            });
            oTable_datatable.fnDraw();
            successNoty('Filtered')
        }
    });

}

function updateTable() {
    $.get(ajaxUrl, function (data) {
        oTable_datatable.fnClearTable();
        $.each(data, function (key, item) {
            oTable_datatable.fnAddData(item);
        });
        oTable_datatable.fnDraw();
    });
}

function save() {
    var form = $('#detailsForm');
    debugger;
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $('#editRow').modal('hide');
            updateTable();
            successNoty('Saved');
        }
    });
}

var failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(text) {
    closeNoty();
    noty({
        text: text,
        type: 'success',
        layout: 'bottomRight',
        timeout: true
    });
}

function failNoty(event, jqXHR, options, jsExc) {
    closeNoty();
    failedNote = noty({
        text: 'Failed: ' + jqXHR.statusText + "<br>",
        type: 'error',
        layout: 'bottomRight'
    });
}
