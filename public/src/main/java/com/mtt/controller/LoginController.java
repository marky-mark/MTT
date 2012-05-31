package com.mtt.controller;

import com.mtt.security.AuthenticatedUserSession;
import com.mtt.security.login.LoginFailure;
import com.mtt.service.UserService;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.mtt.controller.LoginController.PAGE_PATH;

/**
 * Login Controller
 */
@Controller
@RequestMapping(value = PAGE_PATH)
public final class LoginController {

    public static final String PAGE_PATH = "/login";

    public static final String VIEW_NAME = "login";

    public static final String FIELD_SIZE_NAME = "fieldSize";
    public static final int FIELD_SIZE = 100;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticatedUserSession authenticatedUserSession;

    /**
     *
     * @param subject
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showPage(Subject subject, Model model) {

        if (subject.isAuthenticated()) {
            return "redirect:" + DashBoardController.PAGE_PATH;
        }

        model.addAttribute(FIELD_SIZE_NAME, FIELD_SIZE);

        return VIEW_NAME;
    }

    /**
     * Handle the Failed Shiro Login
     * @param model
     * @param loginForm form
     * @param result
     * @return view
     */
    @RequestMapping(method = RequestMethod.POST)
    public String loginFailure(Model model,
                                @ModelAttribute("loginForm") LoginForm loginForm,
                                BindingResult result,
                                LoginFailure failureReason) {

        model.addAttribute(FIELD_SIZE_NAME, FIELD_SIZE);

        if (failureReason.equals(LoginFailure.USER_NOT_FOUND) ) {
            model.addAttribute("userNameError", "no such user - please register");
        } else if (failureReason.equals(LoginFailure.INCORRECT_CREDENTIALS)) {
            model.addAttribute("passwordError", "wrong password please try again");
        }

        return VIEW_NAME;
    }
}
