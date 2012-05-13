package com.mtt.controller;

import com.mtt.bean.CreateTaskBean;
import com.mtt.domain.exception.TaskNotFoundException;
import com.mtt.service.TaskService;
import com.mtt.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DashBoardControllerTest {

    private DashBoardController controller;

    private UserService userService;

    private TaskService taskService;

    @Before
    public void init() {
        controller = new DashBoardController();
        userService = mock(UserService.class);
        taskService = mock(TaskService.class);
        ReflectionTestUtils.setField(controller, "userService", userService);
        ReflectionTestUtils.setField(controller, "taskService", taskService);
    }

    @Test
    public void testShowPage() {

        ModelAndView modelAndView = controller.showPage();

        assertThat(modelAndView.getViewName(), equalTo(DashBoardController.VIEW_NAME));
        assertThat(modelAndView.getModel().containsKey(DashBoardController.TASKS_MODEL_NAME), equalTo(true));
        assertThat(modelAndView.getModel().containsKey(DashBoardController.USER_MODEL_NAME), equalTo(true));

    }

    @Test
    public void testCreateTask() {
        CreateTaskBean createTaskBean = new CreateTaskBean();
        createTaskBean.setDescription("hello");
        createTaskBean.setTitle("title");
        ModelAndView modelAndView = controller.createTask(createTaskBean);

        assertThat(modelAndView.getViewName(), equalTo(DashBoardController.VIEW_NAME));
        assertThat(modelAndView.getModel().containsKey(DashBoardController.TASKS_MODEL_NAME), equalTo(true));
        assertThat(modelAndView.getModel().containsKey(DashBoardController.USER_MODEL_NAME), equalTo(true));
    }

    @Test
    public void testDeleteNULLId() {
        ModelAndView modelAndView = controller.deleteTask(null);

        assertThat(modelAndView.getViewName(), equalTo(DashBoardController.VIEW_NAME));
        assertThat(modelAndView.getModel().containsKey(DashBoardController.TASKS_MODEL_NAME), equalTo(true));
        assertThat(modelAndView.getModel().containsKey(DashBoardController.USER_MODEL_NAME), equalTo(true));
    }

    @Test
    public void testDeleteInvalidId() {
        ModelAndView modelAndView = controller.deleteTask("not an id");

        assertThat(modelAndView.getViewName(), equalTo(DashBoardController.VIEW_NAME));
        assertThat(modelAndView.getModel().containsKey(DashBoardController.TASKS_MODEL_NAME), equalTo(true));
        assertThat(modelAndView.getModel().containsKey(DashBoardController.USER_MODEL_NAME), equalTo(true));
    }

    @Test
    public void testDeleteNonExistingId() {
        when(taskService.delete(1L)).thenThrow(new TaskNotFoundException(1L));
        ModelAndView modelAndView = controller.deleteTask("1");

        assertThat(modelAndView.getViewName(), equalTo(DashBoardController.VIEW_NAME));
        assertThat(modelAndView.getModel().containsKey(DashBoardController.TASKS_MODEL_NAME), equalTo(true));
        assertThat(modelAndView.getModel().containsKey(DashBoardController.USER_MODEL_NAME), equalTo(true));
    }
}
