package com.mtt.test.steps.common;

import com.mtt.test.UrlFactory;
import com.mtt.test.WebDriverFactory;
import com.mtt.test.page.BasePage;
import cucumber.annotation.After;
import cucumber.annotation.Before;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

public class WebStepDefs {

    @Autowired
    private WebDriverFactory webDriverFactory;

    @Autowired
    private UrlFactory urlFactory;

    private WebDriver driver;

    private BasePage currentPage;

    @Before
    public void before() throws Exception {
        driver = webDriverFactory.createInstance();
    }

    @After
    public void after() {
        driver.manage().deleteAllCookies();
        driver.quit();
        driver = null;
    }

    public void get(String relativePath) {
        driver.get(urlFactory.createRequestUrl(relativePath));
    }

    public <P extends BasePage> P createPage(Class<P> pageClass) {
        try {
            currentPage = pageClass.newInstance();
            currentPage.init(driver);
            return (P) currentPage;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public BasePage getCurrentPage() {
        return currentPage;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
