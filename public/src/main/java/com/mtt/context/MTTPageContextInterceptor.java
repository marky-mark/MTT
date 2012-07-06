package com.mtt.context;

import com.mtt.MTTPage;
import org.springframework.asm.MethodAdapter;
import org.springframework.asm.commons.Method;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;

public class MTTPageContextInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {

        //NOTE Spring 3.1
//        ((HandlerMethod)handler).getMethod().getAnnotations() --Method Annotations
//        ((HandlerMethod)handler).getMethod().getDeclaringClass().getAnnotations() --Class Annotations
//      On the above annotations do a .annotationType().equals(MTTPage.class)


        //can use handler.getClass().getMethod(XXX, Parameters).getAnnotations() - for Method annotations


       for (Annotation annotation : handler.getClass().getAnnotations()) {
            if (annotation.annotationType().equals(MTTPage.class)) {
                //DO some prep work for postHandle
            }
       }

        return true;

    }

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView) throws Exception {

        if (modelAndView != null) {

            if (!modelAndView.getViewName().startsWith("redirect:")) {

                for (Annotation annotation : handler.getClass().getAnnotations()) {
                    if (annotation.annotationType().equals(MTTPage.class)) {
                        modelAndView.getModelMap().put("annotationFound", true);
                    }
                }
            } else {
                // If we are redirecting, we decide to clear out all model attributes to prevent them
                // appearing in the URL query string.
            //    modelAndView.getModelMap().clear();
            }
        }

    }
}
