package com.mtt.test.page.components;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WebDropDownSelect implements WebFormComponent{

    private WebElement dropDown;

    private List<WebElement> fieldOptionElements;

    public WebDropDownSelect(WebElement inputElement) {

        dropDown = inputElement;

        //Get the drop down Options of the drop down
        fieldOptionElements = inputElement.findElements(By.tagName("option"));
    }

    public List<WebElement> getOptions() {
        return fieldOptionElements;
    }

    public WebElement getOptionWebElement(String dropDownValue) {

        for(WebElement webElement : fieldOptionElements) {
            if(webElement.getAttribute("value").equals(dropDownValue)) {
                return webElement;
            }
        }

        return null;
    }

    public boolean containsOption(String dropDownValue) {

        if(getOptionWebElement(dropDownValue) != null) {
            return true;
        }

        return false;
    }

    public void selectDropDownOption(String dropDownValue) {

        WebElement option = getOptionWebElement(dropDownValue);

        if(option != null) {
            option.click();
        }
    }

    @Override
    public void detach() {
        dropDown = null;
    }
}
