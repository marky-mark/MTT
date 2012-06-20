package com.mtt.test.page.dashboard;

import com.mtt.test.page.BasePage;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {

    private DashboardCreateTaskForm dashboardCreateTaskForm;

    @Override
    protected String getPageTitle() {
        return "MTT Dashboard";
    }

    @Override
    protected void initInternal(WebDriver driver) {
        dashboardCreateTaskForm = new DashboardCreateTaskForm(driver);
    }

    public DashboardCreateTaskForm getDashboardCreateTaskForm() {
        return dashboardCreateTaskForm;
    }
}
