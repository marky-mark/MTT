package com.mtt.test.steps.user;

import com.mtt.fixtures.Fixture;
import com.mtt.test.page.registration.RegisterPage;
import com.mtt.test.page.registration.SuccessRegistrationPage;
import com.mtt.test.steps.common.WebStepDefs;
import cucumber.annotation.After;
import cucumber.annotation.Before;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import cucumber.runtime.PendingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.subethamail.wiser.Wiser;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class CreateUserSteps {

    private Wiser wiser;

    @Autowired
    @Qualifier("userRegistrationDataSet")
    private Fixture userRegistrationDataSet;

    @Autowired
    private WebStepDefs webStepDefs;

    private RegisterPage registerPage;

    private SuccessRegistrationPage successRegistrationPage;

    @Before("@create-user-fixture")
    public synchronized void init() {
        wiser = new Wiser();
        wiser.setPort(2500);
        wiser.start();
        userRegistrationDataSet.run();
    }

    @After("@create-user-fixture")
    public void teardown() throws Exception {
        if (wiser != null) {
            wiser.stop();
            wiser = null;
        }

        userRegistrationDataSet.cleanUp();
    }

    @Given("^the user is on the registration page$")
    public void onRegPage() {
        webStepDefs.get("/register");
        registerPage = webStepDefs.createPage(RegisterPage.class);
    }

    @Given("^a user enters (.*?) into the (.*?) field$")
    public void entersIn(String value, String fieldName) {

        if (value.equals("nothing")) {
            registerPage.getRegisterForm().getTextField(fieldName).setValue("");
        } else {
            registerPage.getRegisterForm().getTextField(fieldName).setValue(value);
        }
    }

    @Given("^a user enters valid data into the fields$")
    public void userEntersInAllValidData() {
        registerPage.getRegisterForm().getTextField("firstName").setValue("mark");
        registerPage.getRegisterForm().getTextField("lastName").setValue("kelly");
        registerPage.getRegisterForm().getTextField("emailAddress").setValue("mkelly2@gmail.com");
        registerPage.getRegisterForm().getTextField("confirmedEmailAddress").setValue("mkelly2@gmail.com");
        registerPage.getRegisterForm().getTextField("telephoneNumber").setValue("12345678");
        registerPage.getRegisterForm().getTextField("password").setValue("password");
        registerPage.getRegisterForm().getTextField("confirmedPassword").setValue("password");
    }

    @When("^the user submits the form$")
    public void submitsTheForm() {
        registerPage.getRegisterForm().submit();
    }

    @Then("^the user is returned to the registration page$")
    public void returnedPage() {
        registerPage = webStepDefs.createPage(RegisterPage.class);
    }

    @Then("^the (.*?) field error will be \"(.*?)\"$")
    public void checkFieldError(String fieldName, String errorMessage) {
        if (errorMessage.equals("nothing")) {
            assertThat(registerPage.getRegisterForm().getTextField(fieldName).getErrorMessage(), equalTo(""));
        } else {
            assertThat(registerPage.getRegisterForm().getTextField(fieldName).getErrorMessage(), equalTo(errorMessage));
        }
    }

    @Then("^the user is brought to the success page$")
    public void broughtToTheSuccessPage() {
        successRegistrationPage = webStepDefs.createPage(SuccessRegistrationPage.class);
    }
}
