function editTask(taskId) {
    document.getElementById("text-task-"+taskId).hidden = 'true';
    document.getElementById("text-task-"+taskId+"-edit").hidden = '';
}

function unEditTask(taskId) {
    document.getElementById("text-task-"+taskId).hidden = '';
    document.getElementById("text-task-"+taskId+"-edit").hidden = 'true';
}