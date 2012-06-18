package com.mtt.test.steps.user;

import com.mtt.test.page.registration.RegisterPage;
import com.mtt.test.steps.common.WebStepDefs;
import cucumber.annotation.After;
import cucumber.annotation.Before;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import cucumber.runtime.PendingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.subethamail.wiser.Wiser;

public class CreateUserSteps {

    private Wiser wiser;

    @Autowired
    private WebStepDefs webStepDefs;

    private RegisterPage registerPage;

    @Before("@create-user-fixture")
    public synchronized void init() {
        wiser = new Wiser();
        wiser.setPort(2500);
        wiser.start();
    }

    @After("@create-user-fixture")
    public void teardown() throws Exception {
        if (wiser != null) {
            wiser.stop();
            wiser = null;
        }
    }

    @Given("^the user is on the registration page$")
    public void onRegPage() {
        webStepDefs.get("/register");
        registerPage = webStepDefs.createPage(RegisterPage.class);
    }

    @Given("^a user enters (.*?) into the (.*?) field$")
    public void entersIn(String value, String fieldName) {

    }


}
