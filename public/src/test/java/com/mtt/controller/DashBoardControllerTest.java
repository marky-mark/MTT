package com.mtt.controller;

import com.mtt.bean.CreateTaskBean;
import com.mtt.bean.MTTSession;
import com.mtt.cookies.CookieService;
import com.mtt.domain.entity.Task;
import com.mtt.domain.entity.User;
import com.mtt.domain.exception.TaskNotFoundException;
import com.mtt.security.AuthenticatedUserSession;
import com.mtt.service.CacheExampleService;
import com.mtt.service.TaskService;
import com.mtt.service.UserService;
import com.mtt.service.request.CreateTaskRequest;
import com.mtt.service.request.UpdateTaskRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.lang.reflect.Method;
import java.util.HashSet;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * These are mostly test cases to make sure the system handles false values gracefully
 */
public class DashBoardControllerTest {

    private DashBoardController controller;

    private UserService userService;

    private TaskService taskService;

    private AuthenticatedUserSession authenticatedUserSession;

    private HttpServletRequest httpServletRequest;

    private CookieService cookieService;

    private Validator validator;

    private BindingResult bindingResult;

    private MTTSession mttSession;

    private ConversionService conversionService;

    private CacheExampleService cacheExampleService;

    @Before
    public void init() {
        controller = new DashBoardController();
        userService = mock(UserService.class);
        taskService = mock(TaskService.class);
        cookieService = mock(CookieService.class);
        authenticatedUserSession = mock(AuthenticatedUserSession.class);
        httpServletRequest = mock(HttpServletRequest.class);
        validator = mock(Validator.class);
        bindingResult = mock(BindingResult.class);
        mttSession = mock(MTTSession.class);
        conversionService = mock(ConversionService.class);
        cacheExampleService = mock(CacheExampleService.class);
        ReflectionTestUtils.setField(controller, "userService", userService);
        ReflectionTestUtils.setField(controller, "taskService", taskService);
        ReflectionTestUtils.setField(controller, "authenticatedUserSession", authenticatedUserSession);
        ReflectionTestUtils.setField(controller, "cookieService", cookieService);
        ReflectionTestUtils.setField(controller, "validator", validator);
        ReflectionTestUtils.setField(controller, "mttSession", mttSession);
        ReflectionTestUtils.setField(controller, "conversionService", conversionService);
        ReflectionTestUtils.setField(controller, "cacheExampleService", cacheExampleService);
    }

    @Test
    public void testShowPage() {

        when(authenticatedUserSession.getUsername()).thenReturn("mark");
        User user = new User();
        when(userService.find("mark")).thenReturn(user);
        ModelAndView modelAndView = controller.showPage(httpServletRequest);

        assertThat(modelAndView.getViewName(), equalTo(DashBoardController.VIEW_NAME));
        assertThat(modelAndView.getModel().containsKey(DashBoardController.TASKS_MODEL_NAME), equalTo(true));
        assertThat(modelAndView.getModel().containsKey(DashBoardController.USER_MODEL_NAME), equalTo(true));

    }

    @Test
    public void testCreateTask() {
        when(authenticatedUserSession.getUsername()).thenReturn("mark");
        User user = new User();
        when(userService.find("mark")).thenReturn(user);

        CreateTaskBean createTaskBean = new CreateTaskBean();
        createTaskBean.setDescription("hello");
        createTaskBean.setTitle("title");

        when(validator.validate(createTaskBean)).thenReturn(new HashSet<ConstraintViolation<CreateTaskBean>>());
        ModelAndView modelAndView = controller.createTask(createTaskBean);

        assertThat(modelAndView.getViewName(), equalTo(DashBoardController.VIEW_NAME));
        assertThat(modelAndView.getModel().containsKey(DashBoardController.TASKS_MODEL_NAME), equalTo(true));
        assertThat(modelAndView.getModel().containsKey(DashBoardController.USER_MODEL_NAME), equalTo(true));
    }

    @Test
    public void testDeleteNULLId() {

        when(authenticatedUserSession.getUsername()).thenReturn("mark");
        User user = new User();
        when(userService.find("mark")).thenReturn(user);

        ModelAndView modelAndView = controller.deleteTask(null);

        assertThat(modelAndView.getViewName(), equalTo(DashBoardController.VIEW_NAME));
        assertThat(modelAndView.getModel().containsKey(DashBoardController.TASKS_MODEL_NAME), equalTo(true));
        assertThat(modelAndView.getModel().containsKey(DashBoardController.USER_MODEL_NAME), equalTo(true));
    }

    @Test
    public void testDeleteInvalidId() {
        when(authenticatedUserSession.getUsername()).thenReturn("mark");
        User user = new User();
        when(userService.find("mark")).thenReturn(user);

        ModelAndView modelAndView = controller.deleteTask("not an id");

        assertThat(modelAndView.getViewName(), equalTo(DashBoardController.VIEW_NAME));
        assertThat(modelAndView.getModel().containsKey(DashBoardController.TASKS_MODEL_NAME), equalTo(true));
        assertThat(modelAndView.getModel().containsKey(DashBoardController.USER_MODEL_NAME), equalTo(true));
    }

    @Test
    public void testDeleteNonExistingId() {

        when(authenticatedUserSession.getUsername()).thenReturn("mark");
        User user = new User();
        when(userService.find("mark")).thenReturn(user);
        when(taskService.delete(1L)).thenThrow(new TaskNotFoundException(1L));
        ModelAndView modelAndView = controller.deleteTask("1");

        assertThat(modelAndView.getViewName(), equalTo(DashBoardController.VIEW_NAME));
        assertThat(modelAndView.getModel().containsKey(DashBoardController.TASKS_MODEL_NAME), equalTo(true));
        assertThat(modelAndView.getModel().containsKey(DashBoardController.USER_MODEL_NAME), equalTo(true));
    }

    @Test
    public void testUpdateNonExistingId() {

        when(authenticatedUserSession.getUsername()).thenReturn("mark");
        User user = new User();
        when(userService.find("mark")).thenReturn(user);

        UpdateTaskRequest request = new UpdateTaskRequest();
        request.setId(1L);
        when(taskService.update(request)).thenThrow(new TaskNotFoundException(1L));

        ModelAndView modelAndView = controller.updateTask(request);

        assertThat(modelAndView.getViewName(), equalTo(DashBoardController.VIEW_NAME));
        assertThat(modelAndView.getModel().containsKey(DashBoardController.TASKS_MODEL_NAME), equalTo(true));
        assertThat(modelAndView.getModel().containsKey(DashBoardController.USER_MODEL_NAME), equalTo(true));
    }

    @Test
    public void testUpdateCheckInvalidId() {
        when(authenticatedUserSession.getUsername()).thenReturn("mark");
        User user = new User();
        when(userService.find("mark")).thenReturn(user);
        ModelAndView modelAndView = controller.updateCheckOfTask(true, null);

        assertThat(modelAndView.getViewName(), equalTo(DashBoardController.VIEW_NAME));
        assertThat(modelAndView.getModel().containsKey(DashBoardController.TASKS_MODEL_NAME), equalTo(true));
        assertThat(modelAndView.getModel().containsKey(DashBoardController.USER_MODEL_NAME), equalTo(true));
    }

    @Test
    public void testUpdateCheckNonId() {
        when(authenticatedUserSession.getUsername()).thenReturn("mark");
        User user = new User();
        when(userService.find("mark")).thenReturn(user);
        ModelAndView modelAndView = controller.updateCheckOfTask(true, "not an id");

        assertThat(modelAndView.getViewName(), equalTo(DashBoardController.VIEW_NAME));
        assertThat(modelAndView.getModel().containsKey(DashBoardController.TASKS_MODEL_NAME), equalTo(true));
        assertThat(modelAndView.getModel().containsKey(DashBoardController.USER_MODEL_NAME), equalTo(true));
    }

    @Test
    public void testUpdateCheckIdNotInSystem() {
        when(authenticatedUserSession.getUsername()).thenReturn("mark");
        User user = new User();
        when(userService.find("mark")).thenReturn(user);
        when(taskService.find(1L)).thenThrow(new TaskNotFoundException(1L));
        ModelAndView modelAndView = controller.updateCheckOfTask(true, "1");

        assertThat(modelAndView.getViewName(), equalTo(DashBoardController.VIEW_NAME));
        assertThat(modelAndView.getModel().containsKey(DashBoardController.TASKS_MODEL_NAME), equalTo(true));
        assertThat(modelAndView.getModel().containsKey(DashBoardController.USER_MODEL_NAME), equalTo(true));
    }

    @Test
    public void testUpdateCheckHappyPath() {
        when(authenticatedUserSession.getUsername()).thenReturn("mark");
        User user = new User();
        when(userService.find("mark")).thenReturn(user);
        Task task = new Task();
        ReflectionTestUtils.setField(task, "id", 1L);
        when(taskService.find(1L)).thenReturn(task);
        ModelAndView modelAndView = controller.updateCheckOfTask(true, "1");

        assertThat(modelAndView.getViewName(), equalTo(DashBoardController.VIEW_NAME));
        assertThat(modelAndView.getModel().containsKey(DashBoardController.TASKS_MODEL_NAME), equalTo(true));
        assertThat(modelAndView.getModel().containsKey(DashBoardController.USER_MODEL_NAME), equalTo(true));
    }

    @Test
    public void testPrivateConversion() throws Exception {

        Class<?> controllerClass = controller.getClass();

        //get a private method and make it accessible to test
        Method method = controllerClass.getDeclaredMethod("convert", Long.class, CreateTaskBean.class);
        method.setAccessible(true);

        CreateTaskRequest createTaskRequest = (CreateTaskRequest) method.invoke(controller, 1L, new CreateTaskBean("title test", "test description"));

        assertThat(createTaskRequest.getUserId(), equalTo(1L));
        assertThat(createTaskRequest.getTitle(), equalTo("title test"));
        assertThat(createTaskRequest.getDescription(), equalTo("test description"));
    }

}
