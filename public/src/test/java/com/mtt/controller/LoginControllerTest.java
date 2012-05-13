package com.mtt.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LoginControllerTest {

    private LoginController controller;

    @Before
    public void init() {
        controller = new LoginController();
    }

    @Test
    public void testGetPage() {
        ModelAndView modelAndView = controller.showPage();

        assertThat(modelAndView.getViewName(), equalTo(LoginController.VIEW_NAME));
    }
}
