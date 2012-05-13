package com.mtt.controller;

import com.mtt.bean.CreateTaskBean;
import com.mtt.domain.entity.Task;
import com.mtt.domain.entity.User;
import com.mtt.domain.exception.TaskNotFoundException;
import com.mtt.security.AuthenticatedUserSession;
import com.mtt.service.TaskService;
import com.mtt.service.UserService;
import com.mtt.service.request.CreateTaskRequest;
import com.mtt.service.request.UpdateTaskRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

import static com.mtt.controller.DashBoardController.PAGE_PATH;

/**
 * DashBoard Controller for a user to view his/her tasks
 * In addition to be able to delete and create tasks
 */
@Controller
@RequestMapping(value = PAGE_PATH)
public final class DashBoardController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DashBoardController.class);

    public static final String PAGE_PATH = "/dashboard";

    public static final String VIEW_NAME = "dashboard";

    //Beans
    public static final String CREATE_BEAN_NAME = "createTaskBean";
    public static final String UPDATE_BEAN_NAME = "updateTaskBean";

    //Model map
    public static final String USER_MODEL_NAME = "user";
    public static final String TASKS_MODEL_NAME = "tasks";
    public static final String DESC_SIZE_NAME = "descSize";
    public static final int DESC_SIZE = 500;
    public static final String TITLE_SIZE_NAME = "descSize";
    public static final int TITLE_SIZE = 100;

    @Autowired
    public UserService userService;

    @Autowired
    public TaskService taskService;

    @Autowired
    private AuthenticatedUserSession authenticatedUserSession;

    /**
     * @return the page
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showPage() {

        Map<String, Object> map = new HashMap<String, Object>();

        User user = userService.find(authenticatedUserSession.getUsername());

        return populateCommonAttributes(map, user);
    }

    /**
     * to create a new task associated to the user
     * @param createTaskBean task details
     * @return the view
     */
    @RequestMapping(method = RequestMethod.POST, params = "create-task")
    public ModelAndView createTask(@ModelAttribute(CREATE_BEAN_NAME) CreateTaskBean createTaskBean) {

        Map<String, Object> map = new HashMap<String, Object>();
        User user = userService.find(authenticatedUserSession.getUsername());

        taskService.create(convert(user.getId(), createTaskBean));

        return populateCommonAttributes(map, user);
    }

    /**
     * Delete a specific task
     * @param taskId  task to be deleted
     * @return  the view
     */
    @RequestMapping(method = RequestMethod.POST, params = "delete-task")
    public ModelAndView deleteTask(String taskId) {

        Map<String, Object> map = new HashMap<String, Object>();
        User user = userService.find(authenticatedUserSession.getUsername());

        try {
            taskService.delete(new Long(taskId));
        } catch (NumberFormatException e) {
            LOGGER.error("Attempted to delete a task with an invalid id" + taskId);
        } catch (TaskNotFoundException e) {
            LOGGER.error("Attempted to delete a task that does not exist id:" + taskId);
        }

        return populateCommonAttributes(map, user);
    }

    /**
     * Update a user's specified task
     * @param updateTaskBean update details
     * @return the view
     */
    @RequestMapping(method = RequestMethod.POST, params = "update-task")
    public ModelAndView updateTask(@ModelAttribute("updateTaskBean")UpdateTaskRequest updateTaskBean) {

        Map<String, Object> map = new HashMap<String, Object>();
        Long userId = 1L;

        User user = userService.find(authenticatedUserSession.getUsername());

        try {
            taskService.update(updateTaskBean);
        } catch (TaskNotFoundException e) {
            LOGGER.error("Attempted to update a task that does not exist id:" + updateTaskBean.getId());
        }

        return populateCommonAttributes(map, user);
    }

    @RequestMapping(method = RequestMethod.POST/*, params = "update-check-of-task"*/)
    public ModelAndView updateCheckOfTask(boolean checked, String taskId) {
        Map<String, Object> map = new HashMap<String, Object>();
        Long userId = 1L;

        User user = userService.find(authenticatedUserSession.getUsername());

        try {
            Long tId = new Long(taskId);
            Task task = taskService.find(tId);
            UpdateTaskRequest updateTaskRequest = new UpdateTaskRequest();

            //This is the only value that changes
            updateTaskRequest.setChecked(checked);

            //The rest stay the same
            updateTaskRequest.setId(task.getId());
            updateTaskRequest.setTitle(task.getTitle());
            updateTaskRequest.setDescription(task.getDescription());

            taskService.update(updateTaskRequest);
        } catch (NumberFormatException e) {
            LOGGER.error("Attempted to delete a task with an invalid id" + taskId);
        } catch (TaskNotFoundException e) {
            LOGGER.error("Attempted to delete a task that does not exist id:" + taskId);
        }

        return populateCommonAttributes(map, user);
    }

    private ModelAndView populateCommonAttributes(Map<String, Object> map, User user) {
        map.put(USER_MODEL_NAME, user);
        map.put(TASKS_MODEL_NAME, taskService.findByUser(user.getId()));
        map.put(DESC_SIZE_NAME, DESC_SIZE);
        map.put(TITLE_SIZE_NAME, TITLE_SIZE);

        return new ModelAndView(VIEW_NAME, map);
    }

    private CreateTaskRequest convert(Long userId, CreateTaskBean createTaskBean) {
        CreateTaskRequest createTaskRequest = new CreateTaskRequest();
        createTaskRequest.setUserId(userId);
        createTaskRequest.setTitle(createTaskBean.getTitle());
        createTaskRequest.setDescription(createTaskBean.getDescription());

        return createTaskRequest;
    }


}
