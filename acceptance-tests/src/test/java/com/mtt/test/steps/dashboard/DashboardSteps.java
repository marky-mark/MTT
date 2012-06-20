package com.mtt.test.steps.dashboard;

import com.mtt.fixtures.Fixture;
import com.mtt.test.page.dashboard.DashboardDisplayTaskForm;
import com.mtt.test.page.dashboard.DashboardPage;
import com.mtt.test.steps.common.WebStepDefs;
import cucumber.annotation.After;
import cucumber.annotation.Before;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Map;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class DashboardSteps {

    @Autowired
    private WebStepDefs webStepDefs;

    private DashboardPage dashboardPage;

    @Autowired
    @Qualifier("dashboardDataSet")
    private Fixture dashboardDataSet;

    @Before("@dashboard-fixture")
    public synchronized void init(){
        dashboardDataSet.run();
    }

    @After("@dashboard-fixture")
    public void teardown() {
        dashboardDataSet.cleanUp();
    }

    @When("^the user is on the dashboard page$")
    public void onDashBoard() {
        dashboardPage = webStepDefs.createPage(DashboardPage.class);
    }

    @Then("^the user should see the following tasks:$")
    public void tasksThatShouldBeSeen(List<Map<String,String>> taskTable) {

        for(int i = 0; i < taskTable.size(); i++) {

            Map<String,String> task = taskTable.get(i);
            DashboardDisplayTaskForm taskFromPage = dashboardPage.getDashboardDisplayTaskFormList().get(i);

            assertThat(task.get("Title"), equalTo(taskFromPage.getTextField("title_" + (i + 1)).getValue()));
            assertThat(task.get("Description"), equalTo(taskFromPage.getTextArea("description_" + (i + 1)).getValue()));

            if (task.get("Checked").equals("0")) {
                assertThat(taskFromPage.getCheckBox("checked_" + (i + 1)).isChecked(), equalTo(false));
            } else {
                assertThat(taskFromPage.getCheckBox("checked_" + (i + 1)).isChecked(), equalTo(true));
            }
        }
    }

    //TODO tests for updating, creating and deleting tasks
}
