package com.mtt.test.page;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    private WebDriver driver;

    public void init(WebDriver driver) {
        this.driver = driver;

        if (!getPageTitle().equals(driver.getTitle())) {
            throw new IllegalStateException("expected " + getPageTitle() + " but got " + driver.getTitle());
        }

        initInternal(driver);
    }

    public WebDriver getDriver() {
        return driver;
    }

    protected abstract String getPageTitle();

    protected abstract void initInternal(WebDriver driver);
}
