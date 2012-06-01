package com.mtt.controller;

import com.mtt.error.MVCErrorReporter;
import com.mtt.error.ReportableErrors;
import org.springframework.ui.Model;

public abstract class BaseController {

    protected void addErrors(Model model, ReportableErrors reportableErrors) {
        model.addAttribute("errors", new MVCErrorReporter(reportableErrors));
    }
}
