package com.mtt.security.login;

import com.mtt.exception.UserNotRecognizedException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;

public enum LoginFailure {

    USER_NOT_FOUND,
    INCORRECT_CREDENTIALS,
    UNKNOWN;

    public static LoginFailure isFailureFor(AuthenticationException ex) {
        if (ex.getClass().equals(IncorrectCredentialsException.class)) {
            return INCORRECT_CREDENTIALS;
        } else if (ex.getClass().equals(UserNotRecognizedException.class)) {
            return USER_NOT_FOUND;
        }

        return UNKNOWN;
    }
}
