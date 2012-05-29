package com.mtt.security.impl;

import com.mtt.domain.entity.User;
import com.mtt.exception.UserNotRecognizedException;
import com.mtt.security.AuthenticatedUserSession;
import com.mtt.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroSubjectUserSession implements AuthenticatedUserSession {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroSubjectUserSession.class);

    private boolean initialized = false;

    @Autowired
    private UserService userService;

    @Override
    public synchronized String getUsername() {
        init();
        return (String) getSubject().getPrincipal();
    }

    @Override
    public void logoutUser() {

        Subject currentUser = getSubject();

        if (currentUser != null) {
            currentUser.logout();
        }
    }

    @Override
    public boolean userIsAuthenticated() {
        return getSubject().isAuthenticated();
    }

    private void init() {

        mustBeRecognisedUser();

        if (!initialized) {

            getUser();

            initialized = true;
        }
    }

    private User getUser() {
        try {
            return userService.find((String) getSubject().getPrincipal());
        } catch (RuntimeException ex) {
            throw new UserNotRecognizedException(String.valueOf(getSubject().getPrincipal()) );
        }
    }

    private Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    private void mustBeRecognisedUser() {

        if (getSubject().isRemembered() || getSubject().isAuthenticated()) {

            return;
        }

        LOGGER.warn("Found invalid state in Shiro Subject - Subject: " + String.valueOf(getSubject().getPrincipal())
                + ", isRemembered: "
                + getSubject().isRemembered()
                + ", isAuthenticated: " + getSubject().isAuthenticated()
                + ", sessionIsCreated: " + (getSubject().getSession(false) != null));

        throw new UserNotRecognizedException(String.valueOf(getSubject().getPrincipal()) );
    }
}
