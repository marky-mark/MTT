package com.mtt.test.page.login;

import com.mtt.test.page.BaseForm;
import org.openqa.selenium.WebDriver;

public class LoginForm extends BaseForm {

    public LoginForm(WebDriver driver) {
        super(driver);
    }

    @Override
    protected String getSubmitButtonName() {
        return "login";
    }

    @Override
    protected String getFormName() {
        return "login-form";
    }
}
