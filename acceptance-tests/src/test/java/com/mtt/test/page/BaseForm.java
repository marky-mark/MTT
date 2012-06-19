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

        WebElement formElement = driver.findElement(By.xpath("//form[@id='" + getFormName() + "']"));

        List<WebElement> inputElements = formElement.findElements(By.tagName("input"));

        for (WebElement element : inputElements) {
            String inputName = element.getAttribute("name");

            if (element.getAttribute("type").equals("text") || element.getAttribute("type").equals("password")) {
                WebElement errorElement = formElement.findElement(By.id(inputName + "-error"));
                textFields.put(inputName, new WebTextField(element, !errorElement.getText().equals(""), errorElement.getText()));
            } else if (element.getAttribute("type").equals("submit") ) {
                submitButton = element;
            }
        }

        List<WebElement> textAreaElements = formElement.findElements(By.tagName("textarea"));
        List<WebElement> selectElements = formElement.findElements(By.tagName("select"));

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
