package com.mtt.controller;

import com.mtt.domain.entity.User;
import com.mtt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

import static com.mtt.controller.DashBoardController.PAGE_PATH;

@Controller
@RequestMapping(value = PAGE_PATH)
public class DashBoardController {

    public static final String PAGE_PATH = "/dashboard";

    public static final String VIEW_NAME = "dashboard";

    @Autowired
    public UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showPage() {
        Map<String, Object> map = new HashMap<String, Object>();

        User user = userService.find(1L);

        map.put("user", user);

        return new ModelAndView(VIEW_NAME, map);
    }
}
