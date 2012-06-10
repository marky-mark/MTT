function editTask(taskId) {
    document.getElementById("text-task-"+taskId).hidden = 'true';
    document.getElementById("text-task-"+taskId+"-edit").hidden = '';
}

function unEditTask(taskId) {
    document.getElementById("text-task-"+taskId).hidden = '';
    document.getElementById("text-task-"+taskId+"-edit").hidden = 'true';
}

function validateUpdateBean(id) {

    //clear the form first
    $('#title_' + id + '-error').empty();
    $('#description_' + id + '-error').empty();

    var form = document.forms["update-task-" + id + "-form"];
    var checked = 'false';
    console.log($('#checked_'+id + ":checked"));
    if ($('#checked_'+id).attr('checked') == 'checked') {
        checked = 'true';
    }

    var serialisedFormData = "title="+ $('#title_'+id).val() +
                             "&description=" + $('#description_'+id).val() +
                             "&id=" + id +
                             "&checked="+ checked;

    console.log(serialisedFormData);

    $.ajax({
        url: "/ajax" + form.getAttribute('action') + "?update-validate=true",
        type: "POST",
        data: serialisedFormData,
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        dataType: "json",
        success:function(validationResponse){
            console.log(validationResponse);

            var errors = validationResponse.errorReporter.fieldErrors;

            for(var i=0; i < errors.length; i++) {
//                console.log(errors[i].field + " : " + errors[i].message + "\n");
//                console.log('#' + errors[i].field + '_' + id + '-error' + "\n");
                $('#' + errors[i].field + '_' + id + '-error').empty();
                $('#' + errors[i].field + '_' + id + '-error').append(errors[i].message);
            }
        },
        /*A function to be called when the request finishes (after success and error callbacks are executed).
        The function gets passed two arguments: The jqXHR (in jQuery 1.4.x, XMLHTTPRequest) object and a string
        categorizing the status of the request ("success", "notmodified", "error", "timeout", "abort", or "parsererror")*/
        complete:function(jqXHR, textStatus){
            if (textStatus === 'parsererror'){
                window.location.reload();
            }
        }
    });

}

function validateCreateBean() {

    //clear the form first
    $('#title-error').empty();
    $('#description-error').empty();

    var form = document.forms["create-task-form"];

    $.ajax({
        url: "/ajax" + form.getAttribute('action') + "?create-validate=true",
        type: "POST",

        data: "title="+ $('#title').val() +"&description="+ $('#description').val(),
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        dataType: "json",
        success:function(validationResponse){
            var errors = validationResponse.errorReporter.fieldErrors;

            for(var i=0; i < errors.length; i++) {
//                console.log(errors[i].field + " : " + errors[i].message + "\n");
//                console.log('#' + errors[i].field + '-error' + "\n");
                $('#' + errors[i].field + '-error').empty();
                $('#' + errors[i].field + '-error').append(errors[i].message);
            }
        },
        /*A function to be called when the request finishes (after success and error callbacks are executed).
        The function gets passed two arguments: The jqXHR (in jQuery 1.4.x, XMLHTTPRequest) object and a string
        categorizing the status of the request ("success", "notmodified", "error", "timeout", "abort", or "parsererror")*/
        complete:function(jqXHR, textStatus){
            if (textStatus === 'parsererror'){
                window.location.reload();
            }
        }
    });
}