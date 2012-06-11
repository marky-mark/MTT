package com.mtt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.mtt.controller.RegisterUserConfirmationController.PAGE_PATH;

@Controller
@RequestMapping(value = PAGE_PATH)
public class RegisterUserConfirmationController {

    public static final String PAGE_PATH = "/register-confirmation";

    public static final String VIEW_NAME = "register-confirmation";

    //TODO : In Spring 3.1 Redirect Attribute - pass email address
    @RequestMapping(method = RequestMethod.GET)
    public String showPage(Model model, @RequestParam String emailAddress, HttpServletResponse response)  throws IOException {
        model.addAttribute("emailAddress", emailAddress);

        //Check for the refferer for 3.0 and send error response
//        response.sendError(404);
//        return null;

        return VIEW_NAME;
    }
}
