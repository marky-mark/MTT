package com.mtt.test.page;

import com.mtt.test.page.components.WebCheckBox;
import com.mtt.test.page.components.WebDropDownSelect;
import com.mtt.test.page.components.WebTextField;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseForm {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseForm.class);

    protected Map<String, WebTextField> textFields = new HashMap<String, WebTextField>();

    protected Map<String, WebCheckBox> checkBoxes = new HashMap<String, WebCheckBox>();

    protected Map<String, WebDropDownSelect> dropDownSelectBoxes = new HashMap<String, WebDropDownSelect>();

    private WebElement submitButton;

    private String globalErrorMessage;

    protected BaseForm(WebDriver driver) {
        buildFormComponents(driver);
        this.globalErrorMessage = getGlobalError(driver);
    }

     /**
     * This method attempts to extract the text fields and checkBoxes that make up the registration form.
     *
     * @param driver the {@link org.openqa.selenium.WebDriver} representing the registration page.
     */
    protected void buildFormComponents(WebDriver driver) {
        List<WebElement> fieldElements = driver.findElements(By.xpath("//div[contains(@class,\"form-component\")]"));

        for (WebElement element : fieldElements) {
            // TODO: This is a bit of a hack but can't find any easier way of doing it.
            WebElement input;

            try {
                input = element.findElement(By.tagName("input"));
            } catch (NoSuchElementException nsee) {
                try {
                    input = element.findElement(By.tagName("textarea"));
                } catch (NoSuchElementException nseeInner) {
                    input = element.findElement(By.tagName("select"));
                }
            }

            String fieldName = input.getAttribute("name");

            if (containsTextField(element)) {
                String error = getFieldError(element);
                textFields.put(fieldName, new WebTextField(input, error != null, error));
            } else if (containsCheckbox(element)) {
                checkBoxes.put(fieldName, new WebCheckBox(input));
            } else if(containsDropDownSelectBox(element)) {
                dropDownSelectBoxes.put(fieldName,new WebDropDownSelect(element));
            }
        }

        submitButton = driver.findElement(By.name(getSubmitButtonName()));
    }

    private boolean containsTextField(WebElement element) {
        return element.getAttribute("class").contains("form-text")
                || element.getAttribute("class").contains("form-password")
                || element.getAttribute("class").contains("form-input");
    }

    private boolean containsCheckbox(WebElement element) {
        return element.getAttribute("class").contains("form-checkbox");
    }

    private boolean containsDropDownSelectBox(WebElement element) {
        return element.getAttribute("class").contains("form-select");
    }

    private String getFieldError(WebElement element) {
        WebElement label = element.findElement(By.tagName("label"));

        try {

            WebElement errorElement = label.findElement(By.className("error"));

            if (errorElement != null) {
                String errorText = errorElement.getText();
                return element.getAttribute("class").contains("form-component-error")
                        && StringUtils.hasLength(errorText) ? errorText : null;
            }

        } catch (NoSuchElementException e) {
            LOGGER.debug("no error message found");
        }
        return null;
    }

        /**
     * Submit the form.
     */
    public void submit() {

        // Make sure we save state of the fields so that the values can still be obtained even
        // though the underlying Selenium WebElement may have gone stale.

        for (WebTextField textField : textFields.values()) {
            textField.detach();
        }

        for (WebCheckBox checkBox : checkBoxes.values()) {
            checkBox.detach();
        }

        submitButton.submit();
    }

    public WebTextField getTextField(String fieldName) {
        return textFields.get(fieldName);
    }

    public WebCheckBox getCheckBox(String fieldName) {
        return checkBoxes.get(fieldName);
    }

    public WebDropDownSelect getDropDown(String fieldName) {
        return dropDownSelectBoxes.get(fieldName);
    }

    protected String getGlobalError(WebDriver driver) {

        try {

            WebElement formElement = driver.findElement(By.xpath("//form[@id='" + getFormName() + "']"));

            WebElement errorElement = formElement.findElement(By.tagName("span"));
            if (errorElement != null) {
                String className = errorElement.getAttribute("class");
                if (className != null && className.equals("error")) {
                    return errorElement.getText();
                }
            }

        } catch (NoSuchElementException e) {
            LOGGER.debug("No Global Error Found");
        }

        return null;
    }

    public boolean hasGlobalError() {
        return getGlobalErrorMessage() != null;
    }

    public String getGlobalErrorMessage() {
        return globalErrorMessage;
    }

    protected abstract String getSubmitButtonName();

    protected abstract String getFormName();

}
