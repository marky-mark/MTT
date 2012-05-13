package com.mtt.exception;

public class UserNotRecognizedException extends RuntimeException {

    public UserNotRecognizedException(String username) {
        super("User " +  username + " not recognised");
    }
}
