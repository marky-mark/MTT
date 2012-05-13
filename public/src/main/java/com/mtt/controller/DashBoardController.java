package com.mtt.controller;

import com.mtt.bean.CreateTaskBean;
import com.mtt.service.TaskService;
import com.mtt.service.UserService;
import com.mtt.service.request.CreateTaskRequest;
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

    public static final String PAGE_PATH = "/dashboard";

    public static final String VIEW_NAME = "dashboard";

    public static final String BEAN_NAME = "createTaskBean";

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

    /**
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showPage() {

        //TODO: Add Flash Scope...or protect by shiro(prefer)
        Map<String, Object> map = new HashMap<String, Object>();

        Long userId = 1L;

        return populateCommonAttributes(map, userId);
    }

    @RequestMapping(method = RequestMethod.POST, params = "create-task")
    public ModelAndView createTask(@ModelAttribute(BEAN_NAME) CreateTaskBean createTaskBean) {

        Map<String, Object> map = new HashMap<String, Object>();
        Long userId = 1L;

        taskService.create(convert(userId, createTaskBean));

        return populateCommonAttributes(map, userId);
    }

    private ModelAndView populateCommonAttributes(Map<String, Object> map, Long userId) {
        map.put(USER_MODEL_NAME, userService.find(userId));
        map.put(TASKS_MODEL_NAME, taskService.findByUser(userId));
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
