package com.mtt.test.page.dashboard;

import com.mtt.test.page.BaseForm;
import org.openqa.selenium.WebDriver;

public class DashboardDisplayTaskForm extends BaseForm {

    private String formName;

    public DashboardDisplayTaskForm() {
    }

    public DashboardDisplayTaskForm(String formName, WebDriver driver) {
        super(driver);
        this.formName = formName;
    }

    public DashboardDisplayTaskForm(WebDriver driver) {
        super(driver);
    }

    @Override
    protected String getSubmitButtonName() {
        return "update-task-button";
    }

    @Override
    protected String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public void build(WebDriver driver) {
        buildFormComponents(driver);
    }
}
