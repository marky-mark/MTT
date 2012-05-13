function editTask(taskId) {
    document.getElementById("text-task-"+taskId).hidden = 'true';
    document.getElementById("text-task-"+taskId+"-edit").hidden = '';
}

function unEditTask(taskId) {
    document.getElementById("text-task-"+taskId).hidden = '';
    document.getElementById("text-task-"+taskId+"-edit").hidden = 'true';
}

//function(checked) {
//    var request;
//
//    if (window.XMLHttpRequest) {
//        request = new XMLHttpRequest();
//    } else {
//        request = new ActiveXObject("MSXML2.XMLHTTP.3.0");
//    }
//    var url = '/recentAdverts';
//    request.open("POST", url, true);
//    request.send();
//
//    request.onreadystatechange = function() {
//        if (request.readyState == 4) {
//
//            if (request.status == 200) {
//                loading = false;
//                faulty_request = false;
//                if (request.responseText) {
//                    addAdverts(request.responseText);
//                    if (!showingAdverts) {
//                        showingAdverts = true;
//                        showAdverts();
//                        trackJson("loadAdverts","success");
//                    }
//                }
//            }
//        }
//    }
//}