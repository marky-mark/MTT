package com.mtt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.mtt.controller.RegisterUserController.PAGE_PATH;

@Controller
@RequestMapping(value = PAGE_PATH)
public class RegisterUserController {

    public static final String PAGE_PATH = "/register";

    public static final String VIEW_NAME = "register";

    @RequestMapping(method = RequestMethod.GET)
    public String showPage(Model model) {
        return VIEW_NAME;
    }
}
