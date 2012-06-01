package com.mtt.cookies.Impl;

import com.mtt.cookies.CookieLimitExceededException;
import com.mtt.cookies.CookieService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

//
// Setting the age
// to 0 will delete the cookie while giving it a negative value will
// not store the cookie and it will be deleted when the browser is
// closed.
//
@Service
public class CookieServiceImpl implements CookieService {

    @Value("${mtt.cookies.default_domain:mtt.com}")
    private String cookieDomain;

    public void removeCookie(HttpServletResponse response, String name) {
        Cookie cookie = createCookie(name, "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    public void writeSessionCookie(HttpServletResponse response, String name) {
        Cookie cookie = createCookie(name, "");
        cookie.setMaxAge(-1);
        response.addCookie(cookie);
    }

    public void writeNewCookie(HttpServletResponse response,
                              String name,
                              String value,
                              Integer maxAge) {
        try {
            String encoded = URLEncoder.encode(value, "ISO-8859-1");
            encoded = encoded.replaceAll("\\+", "%20");
            if (encoded.length() > 1024) {
                throw new CookieLimitExceededException(name, value);
            }
            Cookie cookie = createCookie(name, encoded);
            if (maxAge != null) {
                cookie.setMaxAge(maxAge);
            }

            response.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public Cookie getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();

        for(Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                return cookie;
            }
        }

        return null;
    }

    private Cookie createCookie(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        /*
         * For local development configure your cookie domain to
         * an empty string. Following RFC 2965 ".localhost" is not
         * a valid cookie domain.
         *
         * NOTE: Please set the /etc/hosts if developing locally to
         * map the localhost to the same domain as cookieDomain
         * Load this domain in the browser otherwise the cookie will
         * NEVER be found (as it is the wrong domain)
         */
        if (StringUtils.hasText(cookieDomain)) {
            cookie.setDomain(cookieDomain);
        }

        return cookie;
    }
}
