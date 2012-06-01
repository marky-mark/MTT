package com.mtt.security.shiro;

import com.mtt.cookies.CookieService;
import com.mtt.security.login.LoginFailure;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class MTTFormAuthenticationFilter extends FormAuthenticationFilter {

    public static final String LOGIN_FAILURE = "login.failure";

    private CookieService cookieService;

    public MTTFormAuthenticationFilter(CookieService cookieService) {
        this.cookieService = cookieService;
    }

    @Override
    public String getFailureKeyAttribute() {
        return LOGIN_FAILURE;
    }

    //When Login Fails - this method is called...add in our own interpretation to be picked up by
    //LoginFailureArgumentResolver
    @Override
    protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
        request.setAttribute(LOGIN_FAILURE, LoginFailure.isFailureFor(ae));
    }

    @Override
    protected boolean onLoginSuccess(
            AuthenticationToken token,
            Subject subject,
            ServletRequest request,
            ServletResponse response) throws Exception {

        //Drop a custom Cookie here for the laugh
        cookieService.writeNewCookie((HttpServletResponse) response, "test", "testValue", 600);

        return super.onLoginSuccess(token, subject, request, response);
    }
}
