package com.mtt.test.page.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WebLink {

    private WebElement link;

    private String textValue;

    private String cssName;

    public WebLink(WebElement inputElement) {

        textValue = inputElement.getText();
        cssName = inputElement.getAttribute("class");
        link = inputElement.findElement(By.tagName("a"));
    }

    public String getTextValue() {
        return textValue;
    }

    public String getUrlValue() {
        return link.getAttribute("href");
    }

    public String getCssClass() {
        return cssName;
    }

    public void click() {
        link.click();
    }
}
