package com.mtt.test.page.registration;

import com.mtt.test.page.BasePage;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {

    private RegisterForm registerForm;

    @Override
    protected String getPageTitle() {
        return "MTT Test";
    }

    @Override
    protected void initInternal(WebDriver driver) {
//        registerForm = new RegisterForm(driver);
    }
}
