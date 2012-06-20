package com.mtt.test.page.dashboard;

import com.mtt.test.page.BaseForm;
import org.openqa.selenium.WebDriver;

public class DashboardCreateTaskForm extends BaseForm {

    public DashboardCreateTaskForm(WebDriver driver) {
        super(driver);
    }

    @Override
    protected String getSubmitButtonName() {
        return "create-task-button";
    }

    @Override
    protected String getFormName() {
        return "create-task-form";
    }
}
