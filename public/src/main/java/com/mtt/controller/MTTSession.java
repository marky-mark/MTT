package com.mtt.controller;

import com.mtt.bean.CreateTaskBean;
import com.mtt.service.request.UpdateTaskRequest;

import java.io.Serializable;

public interface MTTSession extends Serializable {

    public void clearCreateTaskSession();

    public void setCreateSession(CreateTaskBean createTaskBean);

    public void setUpdateTaskSession(UpdateTaskRequest updateTaskRequest);

    public void clearUpdateTaskSession(Long taskId);

    public void clearAllTaskSessions();

    public void clearAll();
}
