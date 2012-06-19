package com.mtt.test.page.registration;

import com.mtt.test.page.BaseForm;
import org.openqa.selenium.WebDriver;

public class RegisterForm extends BaseForm {

    public RegisterForm(WebDriver driver) {
        super(driver);
    }

    @Override
    protected String getSubmitButtonName() {
        return "register-user";
    }

    @Override
    protected String getFormName() {
        return "register-form";
    }
}
