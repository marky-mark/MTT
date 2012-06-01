package com.mtt.security.login;

import com.mtt.exception.MissingPasswordException;
import com.mtt.exception.MissingUsernameAndPasswordException;
import com.mtt.exception.MissingUsernameException;
import com.mtt.exception.UserNotRecognizedException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;

public enum LoginFailure {

    USER_NOT_FOUND,
    INCORRECT_CREDENTIALS,
    NO_USER_NAME,
    NO_PASSWORD,
    NO_USERNAME_PASSWORD,
    UNKNOWN;

    public static LoginFailure isFailureFor(AuthenticationException ex) {
        if (ex.getClass().equals(IncorrectCredentialsException.class)) {
            return INCORRECT_CREDENTIALS;
        } else if (ex.getClass().equals(UserNotRecognizedException.class)) {
            return USER_NOT_FOUND;
        } else if (ex.getClass().equals(MissingUsernameException.class)) {
            return NO_USER_NAME;
        } else if (ex.getClass().equals(MissingPasswordException.class)) {
            return NO_PASSWORD;
        } else if (ex.getClass().equals(MissingUsernameAndPasswordException.class)) {
            return NO_USERNAME_PASSWORD;
        }

        return UNKNOWN;
    }
}
