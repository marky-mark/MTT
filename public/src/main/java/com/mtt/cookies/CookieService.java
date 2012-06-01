package com.mtt.cookies;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CookieService {

    public void removeCookie(HttpServletResponse response, String name);

    public void writeSessionCookie(HttpServletResponse response, String name);

    public void writeNewCookie(HttpServletResponse response,
                              String name,
                              String value,
                              Integer maxAge);

    public Cookie getCookie(HttpServletRequest request, String cookieName);
}
