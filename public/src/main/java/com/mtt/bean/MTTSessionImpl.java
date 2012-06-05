package com.mtt.bean;

import com.mtt.service.request.UpdateTaskRequest;

import java.util.HashMap;
import java.util.Map;

public class MTTSessionImpl implements MTTSession {

    private CreateTaskBean taskBean;

    private Map<Long, UpdateTaskRequest> updateTaskRequestList = new HashMap<Long, UpdateTaskRequest>();

    @Override
    public void clearCreateTaskSession() {
        taskBean = null;
    }

    @Override
    public void setCreateSession(CreateTaskBean createTaskBean) {
        taskBean = createTaskBean;
    }

    @Override
    public void setUpdateTaskSession(UpdateTaskRequest updateTaskRequest) {
        updateTaskRequestList.put(updateTaskRequest.getId(), updateTaskRequest);
    }

    @Override
    public void clearUpdateTaskSession(Long taskId) {
        updateTaskRequestList.remove(taskId);
    }

    @Override
    public void clearAllTaskSessions() {
        updateTaskRequestList = new HashMap<Long, UpdateTaskRequest>();
    }

    @Override
    public void clearAll() {
        clearCreateTaskSession();
        clearAllTaskSessions();
    }

    @Override
    public CreateTaskBean getCreateTaskBean() {
        return taskBean;
    }

    @Override
    public UpdateTaskRequest getTaskRequest(Long id) {
        return updateTaskRequestList.get(id);
    }
}
