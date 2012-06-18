package com.mtt.test;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DefaultWebDriverFactory implements WebDriverFactory {

    @Value("${selenium.driver:HTML_UNIT}")
    private String driverType;

    @Override
    public WebDriver createInstance() {
        return WebDriverType.valueOf(driverType).createInstance();
    }
}
