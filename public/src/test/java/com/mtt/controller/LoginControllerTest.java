package com.mtt.controller;

import com.mtt.service.UserService;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginControllerTest {

    private LoginController controller;

    private UserService userService;

    private Subject subject;

    private Model model;
    private BindingResult result;


    @Before
    public void init() {
        model = mock(Model.class);
        result = mock(BindingResult.class);
        controller = new LoginController();
        userService = mock(UserService.class);
        subject = mock(Subject.class);
        ReflectionTestUtils.setField(controller, "userService", userService);
    }

    @Test
    public void testGetPage() {
        when(subject.isAuthenticated()).thenReturn(false);
        String viewName = controller.showPage(subject, model);
        assertThat(viewName, equalTo(LoginController.VIEW_NAME));
    }

    @Test
    public void testGetPageRedirectToDashboardIfLoggedIn() {
        when(subject.isAuthenticated()).thenReturn(true);
        String viewName = controller.showPage(subject, model);
        assertThat(viewName, equalTo("redirect:" + DashBoardController.PAGE_PATH));
    }

//    @Test
//    public void testUserFailure() {
//        LoginForm loginForm = new LoginForm();
//        loginForm.setPassword("pass");
//        loginForm.setUsername("mark");
//        when(userService.find("mark")).thenThrow(new UserNotFoundException("mark"));
//        String view = controller.loginFailure(model, loginForm, result);
//
//        assertThat(view, equalTo(LoginController.VIEW_NAME));
//
//        verify(model).addAttribute(eq(LoginController.FIELD_SIZE_NAME), eq(LoginController.FIELD_SIZE));
//        verify(model).addAttribute(eq("userNameError"), eq("no such user - please register"));
//    }
//
//    @Test
//    public void testPasswordFailure() {
//        LoginForm loginForm = new LoginForm();
//        loginForm.setPassword("pass");
//        loginForm.setUsername("mark");
//        when(userService.find("mark")).thenReturn(new User());
//        when(userService.authenticate("mark", "pass")).thenThrow(new IncorrectCredentialsException());
//        String view = controller.loginFailure(model, loginForm, result);
//
//        assertThat(view, equalTo(LoginController.VIEW_NAME));
//
//        verify(model).addAttribute(eq(LoginController.FIELD_SIZE_NAME), eq(LoginController.FIELD_SIZE));
//        verify(model).addAttribute(eq("passwordError"), eq("wrong password please try again"));
//    }
}
