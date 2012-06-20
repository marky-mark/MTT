package com.mtt.test.page.components;

import org.openqa.selenium.WebElement;

public class WebTextArea implements WebFormComponent {

    private WebElement inputElement;

    private String value;

    private Boolean hasError;

    private String errorMessage;

    public WebTextArea(WebElement inputElement, Boolean hasError, String errorMessage) {
        this.inputElement = inputElement;
        this.hasError = hasError;
        this.errorMessage = errorMessage;
    }

    public String getValue() {
        if (value != null) {
            return value;
        }
        return inputElement.getText();
    }

    public void setValue(String value) {
        if (this.value != null) {
            throw new IllegalStateException("This component is stale");
        }
        inputElement.clear();
        inputElement.sendKeys(value);
    }

    public Boolean hasError() {
        return hasError;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public void detach() {
        value = getValue();
        inputElement = null;
    }

}
