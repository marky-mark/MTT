package com.mtt.test.page.login;

import com.mtt.test.page.BasePage;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private LoginForm loginForm;

    @Override
    protected String getPageTitle() {
        return "MTT Login";
    }

    @Override
    protected void initInternal(WebDriver driver) {
        loginForm = new LoginForm(driver);
    }

    public LoginForm getLoginForm() {
        return loginForm;
    }
}
