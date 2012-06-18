package com.mtt.test.steps.user;

import cucumber.annotation.After;
import cucumber.annotation.Before;
import cucumber.annotation.en.Given;
import org.subethamail.wiser.Wiser;

public class CreateUserSteps {

    private Wiser wiser;

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

    @Given("a user enters (.*?) into the (.*?) field")
    public void givenUserEntersAValueIntoField(String value, String fieldName) {


    }
}
