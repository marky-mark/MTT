package com.mtt.security.login;

import com.mtt.error.ErrorReporter;
import com.mtt.error.ReportableErrors;
import com.mtt.exception.MissingPasswordException;
import com.mtt.exception.MissingUsernameAndPasswordException;
import com.mtt.exception.MissingUsernameException;
import com.mtt.exception.UserNotRecognizedException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;

public enum LoginFailure implements ReportableErrors {

    USER_NOT_FOUND() {
        @Override
        public void report(ErrorReporter errorReporter) {
            errorReporter.fieldError(usernameField, "username is not found - please register");
        }
    },
    INCORRECT_CREDENTIALS() {
        @Override
        public void report(ErrorReporter errorReporter) {
            errorReporter.fieldError(passwordField, "password you have entered is incorrect, please try again");
        }
    },
    NO_USER_NAME() {
        @Override
        public void report(ErrorReporter errorReporter) {
            errorReporter.fieldError(usernameField, "Please enter a username");
        }
    },
    NO_PASSWORD() {
        @Override
        public void report(ErrorReporter errorReporter) {
            errorReporter.fieldError(passwordField, "please enter a password");
        }
    },
    NO_USERNAME_PASSWORD() {
        @Override
        public void report(ErrorReporter errorReporter) {
            errorReporter.fieldError(usernameField, "plase enter a username");
            errorReporter.fieldError(passwordField, "please enter a password");
        }
    },
    UNKNOWN() {
        @Override
        public void report(ErrorReporter errorReporter) {
            errorReporter.globalError("Unknown reason - this is reported, please try again later");
        }
    };

    private static final String usernameField = "username";
    private static final String passwordField = "password";

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
