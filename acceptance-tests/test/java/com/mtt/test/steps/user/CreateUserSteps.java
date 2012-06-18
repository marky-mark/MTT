package com.mtt.test.steps.user;

import cucumber.annotation.After;
import cucumber.annotation.Before;

public class CreateUserSteps {

    private Wiser wiser;

    @Before("@create-user-fixture")
    public synchronized void init() {
        userTester = new HttpClientUserTester(apiHost, new DefaultHttpClient(new ThreadSafeClientConnManager()));
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
}
