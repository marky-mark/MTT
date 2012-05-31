package com.mtt.security.login;

import com.mtt.security.shiro.MTTFormAuthenticationFilter;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletRequest;

public final class LoginFailureArgumentResolver implements WebArgumentResolver {

    @Override
    public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest webRequest) throws Exception {

        if (methodParameter.getParameterType().equals(LoginFailure.class)) {

            //this basically holds the exception which caused the login failure
            return ((HttpServletRequest) webRequest.getNativeRequest())
                .getAttribute(MTTFormAuthenticationFilter.LOGIN_FAILURE);
        }

        return UNRESOLVED;
    }
}

