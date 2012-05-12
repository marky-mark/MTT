package com.mtt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.mtt.controller.LoginController.PAGE_PATH;

@Controller
@RequestMapping(value = PAGE_PATH)
public class LoginController {

    public static final String PAGE_PATH = "/login";

    public static final String VIEW_NAME = "login";

    @RequestMapping(method = RequestMethod.GET)
    public String showPage() {
        return VIEW_NAME;
    }
}
