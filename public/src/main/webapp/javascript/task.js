function editTask(taskId) {
    document.getElementById("text-task-"+taskId).hidden = 'true';
    document.getElementById("text-task-"+taskId+"-edit").hidden = '';
}

function unEditTask(taskId) {
    document.getElementById("text-task-"+taskId).hidden = '';
    document.getElementById("text-task-"+taskId+"-edit").hidden = 'true';
}



function validateCreateBean() {
    $.ajax({
        url: "/ajax/dashboard?validate=true",
        type: "POST",
        data: "title=hello&description=hello2",
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        dataType: "json",
        success:function(validationResponse){
            console.log('got it');
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