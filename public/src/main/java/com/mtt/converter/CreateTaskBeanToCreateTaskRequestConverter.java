package com.mtt.converter;

import com.mtt.bean.CreateTaskBean;
import com.mtt.domain.entity.User;
import com.mtt.security.AuthenticatedUserSession;
import com.mtt.service.UserService;
import com.mtt.service.request.CreateTaskRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public final class CreateTaskBeanToCreateTaskRequestConverter implements Converter<CreateTaskBean, CreateTaskRequest> {

    @Autowired
    public UserService userService;

    @Autowired
    private AuthenticatedUserSession authenticatedUserSession;

    public CreateTaskRequest convert(CreateTaskBean createTaskBean) {
        CreateTaskRequest createTaskRequest = new CreateTaskRequest();

        User user = userService.find(authenticatedUserSession.getUsername());

        createTaskRequest.setUserId(user.getId());
        createTaskRequest.setTitle(createTaskBean.getTitle());
        createTaskRequest.setDescription(createTaskBean.getDescription());

        return createTaskRequest;
    }
}
