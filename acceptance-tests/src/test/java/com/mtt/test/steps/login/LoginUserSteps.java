package com.mtt.test.steps.login;

import com.mtt.fixtures.Fixture;
import com.mtt.test.page.dashboard.DashboardPage;
import com.mtt.test.page.login.LoginPage;
import com.mtt.test.steps.common.WebStepDefs;
import cucumber.annotation.After;
import cucumber.annotation.Before;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class LoginUserSteps {

    @Autowired
    private WebStepDefs webStepDefs;

    private LoginPage loginPage;

    private DashboardPage dashboardPage;

    @Autowired
    @Qualifier("userLoginDataSet")
    private Fixture userLoginDataSet;

    @Before("@login-user-fixture")
    public synchronized void init() {
        userLoginDataSet.run();
    }

    @After("@login-user-fixture")
    public void tearDown() {
        userLoginDataSet.cleanUp();
    }

    @Given("^the user is on the login page$")
    public void userVisitsLoginPage() {
        webStepDefs.get("/login");
        loginPage = webStepDefs.createPage(LoginPage.class);
    }

    @Given("^the user is not logged in$")
    public void userNotLoggedIn() {
        webStepDefs.clearCookies();
    }

    @When("^the user enters (.*?) into the (.*?) field$")
    public void entersIn(String value, String fieldName) {

        if (value.equals("nothing")) {
            loginPage.getLoginForm().getTextField(fieldName).setValue("");
        } else {
            loginPage.getLoginForm().getTextField(fieldName).setValue(value);
        }
    }

    @When("^the user submits the login form$")
    public void submitForm() {
        loginPage.getLoginForm().submit();
    }

    @Then("^the user is redirected back to the login page$")
    public void returnedPage() {
        loginPage = webStepDefs.createPage(LoginPage.class);
    }

    @Then("^the (.*?) field has the error (.*?)$")
    public void checkFieldError(String fieldName, String errorMessage) {
        if (errorMessage.equals("nothing")) {
            assertThat(loginPage.getLoginForm().getTextField(fieldName).getErrorMessage(), equalTo(""));
        } else {
            assertThat(loginPage.getLoginForm().getTextField(fieldName).getErrorMessage(), equalTo(errorMessage));
        }
    }

    @When("^the user tries to access the dashboard page$")
    public void accessTheDashBoardPage() {
        webStepDefs.get("/dashboard");
    }

    @Then("^the user is redirected to the dashboard page$")
    public void onDashBoardPage() {
        dashboardPage = webStepDefs.createPage(DashboardPage.class);
    }
}
