package com.mtt.controller;

import com.mtt.domain.entity.User;
import com.mtt.domain.exception.UserNotFoundException;
import com.mtt.service.UserService;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

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

    /**
     * get the Login page
     * @return view
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showPage() {

        Map<String, Object> map = new HashMap<String, Object>();

        map.put(FIELD_SIZE_NAME, FIELD_SIZE);

        return new ModelAndView(VIEW_NAME, map);
    }

    /**
     * Handle the Failed Shiro Login
     * @param loginForm form
     * @return view
     */
    @RequestMapping(method = RequestMethod.POST)
    public String loginFailure(Model model,
                                     @ModelAttribute("loginForm") LoginForm loginForm,
                                     BindingResult result) {

        //Find out why this failed
        //TODO: preferable it would be better to have a Failure intercepter which can add the error to the request

        model.addAttribute(FIELD_SIZE_NAME, FIELD_SIZE);

        try {
            User user = userService.find(loginForm.getUsername());

            user = userService.authenticate(loginForm.getUsername(), loginForm.getPassword());
        } catch (UserNotFoundException e) {
            result.rejectValue("username", "no such user - please register");
            model.addAttribute("userNameError", "no such user - please register");
            //TODO: Redirect to register page instead
        } catch (IncorrectCredentialsException e) {
            result.rejectValue("password", "wrong password please try again");
            model.addAttribute("passwordError", "wrong password please try again");
        }

        return VIEW_NAME;
    }
}
