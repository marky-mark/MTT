package com.mtt.controller;

import com.mtt.error.MVCErrorReporter;
import com.mtt.error.ReportableErrors;
import org.springframework.ui.Model;

import javax.validation.ConstraintViolation;
import java.util.Map;
import java.util.Set;

public abstract class BaseController {

    protected void addErrors(Model model, ReportableErrors reportableErrors) {
        model.addAttribute("errors", new MVCErrorReporter(reportableErrors));
    }

    protected void addErrors(Map<String, Object> model, Set<? extends ConstraintViolation<?>> violations) {
       model.put("errors", new MVCErrorReporter(violations));
    }

    protected void addErrors(Map<String, Object> model, Set<? extends ConstraintViolation<?>> violations, Long taskId) {
       model.put("errors", new MVCErrorReporter(violations, taskId));
    }
}
