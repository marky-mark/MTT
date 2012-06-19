package com.mtt.test.page.registration;

import com.mtt.test.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SuccessRegistrationPage extends BasePage {

    private String successText;

    @Override
    protected String getPageTitle() {
        return "MTT Registration Confirmation";
    }

    @Override
    protected void initInternal(WebDriver driver) {
        successText = driver.findElement(By.id("success-message")).getText();
    }

    public String getSuccessText() {
        return successText;
    }
}
