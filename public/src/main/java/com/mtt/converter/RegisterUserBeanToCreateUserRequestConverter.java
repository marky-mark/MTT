package com.mtt.converter;

import com.mtt.bean.RegisterUserBean;
import com.mtt.service.request.CreateUserRequest;
import org.springframework.core.convert.converter.Converter;

public class RegisterUserBeanToCreateUserRequestConverter implements Converter<RegisterUserBean, CreateUserRequest> {

    @Override
    public CreateUserRequest convert(RegisterUserBean registerUserBean) {
        CreateUserRequest userRequest = new CreateUserRequest();
        userRequest.setUserName(registerUserBean.getEmailAddress());
        userRequest.setFirstName(registerUserBean.getFirstName());
        userRequest.setLastName(registerUserBean.getLastName());
        userRequest.setTelephoneNumber(registerUserBean.getEmailAddress());
        userRequest.setTelephoneNumber(registerUserBean.getTelephoneNumber());
        userRequest.setPlainTextPassword(registerUserBean.getPassword());

        return userRequest;
    }
}
