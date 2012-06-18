package com.mtt.test;

import org.openqa.selenium.WebDriver;

/**
 */
public enum WebDriverType {

    HTML_UNIT("org.openqa.selenium.htmlunit.HtmlUnitDriver"),
    FIREFOX("org.openqa.selenium.firefox.FirefoxDriver");

    private String driverClass;

    WebDriverType(String driverClass) {
        this.driverClass = driverClass;
    }

    WebDriver createInstance() {
        try {
            return (WebDriver) Class.forName(driverClass).newInstance();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
