package com.mtt.controller;

import com.mtt.bean.RegisterUserBean;
import com.mtt.validation.groups.RegisterUserBeanValidationGroups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static com.mtt.controller.RegisterUserController.PAGE_PATH;

@Controller
@RequestMapping(value = PAGE_PATH)
public class RegisterUserController extends BaseController {

    public static final String PAGE_PATH = "/register";

    public static final String VIEW_NAME = "register";

    @Autowired
    private Validator validator;

    @RequestMapping(method = RequestMethod.GET)
    public String showPage(@ModelAttribute("registerUserBean") RegisterUserBean registerUserBean) {
        return VIEW_NAME;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String postToPage(Model model, @ModelAttribute("registerUserBean") RegisterUserBean registerUserBean) {

        Set<ConstraintViolation<RegisterUserBean>> violations =
                validator.validate(registerUserBean,
                                   RegisterUserBeanValidationGroups.FirstName.class,
                                   RegisterUserBeanValidationGroups.LastName.class,
                                   RegisterUserBeanValidationGroups.Telephone.class,
                                   RegisterUserBeanValidationGroups.EmailAddress.class,
                                   RegisterUserBeanValidationGroups.ConfirmedEmailAddress.class,
                                   RegisterUserBeanValidationGroups.Password.class,
                                   RegisterUserBeanValidationGroups.ConfirmedPassword.class);

        if (violations.size() > 0) {
            addErrors(model, violations);
            return VIEW_NAME;
        }

        //TODO: register the user - trigger an event for email activation
        model.addAttribute("emailAddress", registerUserBean.getEmailAddress());

        return "redirect:" + RegisterUserConfirmationController.PAGE_PATH;
    }
}
