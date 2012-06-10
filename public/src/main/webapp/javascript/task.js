function editTask(taskId) {
    document.getElementById("text-task-"+taskId).hidden = 'true';
    document.getElementById("text-task-"+taskId+"-edit").hidden = '';
}

function unEditTask(taskId) {
    document.getElementById("text-task-"+taskId).hidden = '';
    document.getElementById("text-task-"+taskId+"-edit").hidden = 'true';
}



function validateCreateBean() {

    var form = document.forms["create-task-form"];

    $.ajax({
        url: "/ajax" + form.getAttribute('action') + "?validate=true",
        type: "POST",

        data: "title="+ $('#title').val() +"&description="+ $('#description').val(),
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        dataType: "json",
        success:function(validationResponse){
            var errors = validationResponse.errorReporter.fieldErrors;

            for(var i=0; i < errors.length; i++) {
                console.log(errors[i].field + " : " + errors[i].message + "\n");
                console.log('#' + errors[i].field + '-error' + "\n");
                $('#' + errors[i].field + '-error').empty();
                $('#' + errors[i].field + '-error').append(errors[i].message);
            }
        },
        complete:function(jqXHR, textStatus){
            if (textStatus === 'parsererror'){
                window.location.reload();
            }
        }
    });

}

//var request;
//
//var oForm = document.getElementById('subscribe_frm');
//
//if (window.XMLHttpRequest) {
//    request = new XMLHttpRequest();
//} else {
//    request = new ActiveXObject("MSXML2.XMLHTTP.3.0");
//}
//var url = '/ajax-validate-create';
//request.open("POST", url, true);
//request.setRequestHeader('Content-Type', 'application/json');
//request.send('{\"title\"=\"test\",\"description\"=\"test2\"}');
//
//request.onreadystatechange = function() {
//    if (request.readyState == 4) {
//        if(request.status == 200) {
//            if (request.responseText) {
//
//            }
//        }
//    }
//}