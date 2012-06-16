package com.mtt.controller;

import com.mtt.service.RegistrationService;
import com.mtt.service.exception.UserActivationKeyExpired;
import com.mtt.service.exception.UserActivationKeyUnknown;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static com.mtt.controller.UserActivationController.PAGE_PATH;

@Controller
@RequestMapping(value = PAGE_PATH)
public class UserActivationController extends BaseController {

    public static final String PAGE_PATH = "/activate";

    public static final String VIEW = "activate";

    @Autowired
    private RegistrationService registrationService;

    @RequestMapping(method = RequestMethod.GET)
    public String showPage(Model model, @RequestParam("id") String username, @RequestParam("key") String key) {

        try {
            registrationService.activateUser(key);
            model.addAttribute("activationSentence", "Thank you for activating your account");
        } catch (UserActivationKeyExpired e) {
            model.addAttribute("activationSentence", "The activation key has expired");
        } catch (UserActivationKeyUnknown e) {
            model.addAttribute("activationSentence", "unknown activation key");
        }

        return VIEW;
    }
}
