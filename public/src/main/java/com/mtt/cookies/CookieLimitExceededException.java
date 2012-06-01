package com.mtt.cookies;

public class CookieLimitExceededException extends RuntimeException {

    public CookieLimitExceededException(String cookieName, String value) {
        super("The cookie '"
                       + cookieName
                       + "' would contain "
                       + value.length()
                       + " bytes. The value is '"
                       + value
                       + "'.");
    }
}
