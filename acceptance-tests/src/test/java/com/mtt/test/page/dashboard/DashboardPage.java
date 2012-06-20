package com.mtt.test.page.dashboard;

import com.mtt.test.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class DashboardPage extends BasePage {

    private DashboardCreateTaskForm dashboardCreateTaskForm;

    private List<DashboardDisplayTaskForm> dashboardDisplayTaskFormList;

    @Override
    protected String getPageTitle() {
        return "MTT Dashboard";
    }

    @Override
    protected void initInternal(WebDriver driver) {

        dashboardDisplayTaskFormList = new ArrayList<DashboardDisplayTaskForm>();

        dashboardCreateTaskForm = new DashboardCreateTaskForm(driver);
        List<WebElement> divElements = driver.findElements(By.name("display-task-div"));

        for (WebElement divs : divElements) {
            DashboardDisplayTaskForm dashboardDisplayTaskForm = new DashboardDisplayTaskForm();
            dashboardDisplayTaskForm.setFormName(divs.findElement(By.tagName("form")).getAttribute("id"));
            dashboardDisplayTaskForm.build(driver);
            dashboardDisplayTaskFormList.add(dashboardDisplayTaskForm);
        }
    }

    public DashboardCreateTaskForm getDashboardCreateTaskForm() {
        return dashboardCreateTaskForm;
    }

    public List<DashboardDisplayTaskForm> getDashboardDisplayTaskFormList() {
        return dashboardDisplayTaskFormList;
    }
}
