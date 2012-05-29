package com.mtt.controller;

import com.mtt.security.AuthenticatedUserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.mtt.controller.LogoutController.PAGE_PATH;

/**
 * Controller to Logout the current user
 */
@RequestMapping(PAGE_PATH)
@Controller
public final class LogoutController {

    public static final String PAGE_PATH = "/logoutCurrentUser";

    @Autowired
    private AuthenticatedUserSession authenticatedUserSession;

    @RequestMapping(method = RequestMethod.GET)
    public String logoutCurrentUser() {
        authenticatedUserSession.logoutUser();
        return ("redirect:" + LoginController.PAGE_PATH);
    }
}
