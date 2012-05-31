package com.mtt.security.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

public class SubjectArgumentResolver implements WebArgumentResolver {

    @Override
    public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest webRequest) throws Exception {

        if (methodParameter.getParameterType().equals(Subject.class)) {
            return SecurityUtils.getSubject();
        }

        return UNRESOLVED;
    }
}
