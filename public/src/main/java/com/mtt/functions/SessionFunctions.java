package com.mtt.functions;

import com.mtt.bean.CreateTaskBean;
import com.mtt.bean.MTTSession;
import com.mtt.service.request.UpdateTaskRequest;

public class SessionFunctions {

    public static Boolean hasCreateTaskBean(MTTSession mttSession) {
        return mttSession.getCreateTaskBean() != null;
    }

    public static CreateTaskBean getCreateTaskBean(MTTSession mttSession) {
        return mttSession.getCreateTaskBean();
    }

    public static Boolean hasTaskBean(MTTSession mttSession, Long id) {
        return mttSession.getTaskRequest(id) != null;
    }

    public static UpdateTaskRequest getTaskBean(MTTSession mttSession, Long id) {
        return mttSession.getTaskRequest(id);
    }
}
