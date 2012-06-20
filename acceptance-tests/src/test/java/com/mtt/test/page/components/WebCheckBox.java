package com.mtt.test.page.components;

import org.openqa.selenium.WebElement;

/**
 */
public class WebCheckBox implements WebFormComponent {

    private WebElement inputElement;

    private Boolean checked;

    public WebCheckBox(WebElement inputElement) {
        this.inputElement = inputElement;
    }

    public boolean isChecked() {
        if (checked != null) {
            return checked;
        }

        return inputElement.getAttribute("checked") != null;
    }

    public void setChecked(boolean checked) {
        if (this.checked != null) {
            throw new IllegalStateException("This component is stale");
        }

        if (checked) {
            if (!inputElement.isSelected()) {
                inputElement.click();
            }
        } else {
            if (inputElement.isSelected()) {
                inputElement.click();
            }
        }
    }

    @Override
    public void detach() {
        checked = isChecked();
        inputElement = null;
    }
}
