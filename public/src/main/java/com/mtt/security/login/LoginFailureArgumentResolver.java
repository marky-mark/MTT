package com.mtt.security.login;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

public final class LoginFailureArgumentResolver implements WebArgumentResolver {

    @Override
    public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest webRequest) throws Exception {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}

