package com.mtt.test.page.registration;

import com.mtt.test.page.BaseForm;
import org.openqa.selenium.WebDriver;

public class RegisterForm extends BaseForm {

    public RegisterForm(WebDriver driver) {
        super(driver);
    }

    @Override
    protected String getSubmitButtonName() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected String getFormName() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
