package com.mtt.controller;

import org.springframework.stereotype.Controller;
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
}
