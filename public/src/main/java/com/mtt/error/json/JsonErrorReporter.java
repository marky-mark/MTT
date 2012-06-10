package com.mtt.error.json;

import com.mtt.error.ErrorReporter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

public class JsonErrorReporter implements ErrorReporter {

    private String globalErrorMessage;

    private List<JsonComponentError> fieldErrors = new ArrayList<JsonComponentError>();

    public JsonErrorReporter(BindingResult bindingResult) {
        for (FieldError error : bindingResult.getFieldErrors()) {
            fieldError(error.getField(), error.getDefaultMessage());
        }

        if (bindingResult.hasGlobalErrors()) {
            globalError(bindingResult.getGlobalError().getDefaultMessage());
        }
    }

    @Override
    public void fieldError(String fieldName, String fieldErrorMessage) {
        fieldErrors.add(new JsonComponentError(fieldName, fieldErrorMessage));
    }

    public List<JsonComponentError> getFieldErrors() {
        return fieldErrors;
    }

    @Override
    public String getFieldError(String fieldName) {

        JsonComponentError componentWrapper = new JsonComponentError(fieldName);

        if (fieldErrors.contains(componentWrapper)) {
            return fieldErrors.get(fieldErrors.indexOf(componentWrapper)).getMessage();
        }

        return null;
    }

    @Override
    public void globalError(String globalErrorMessage) {
        this.globalErrorMessage = globalErrorMessage;
    }

    @Override
    public String getGlobalError() {
        return globalErrorMessage;
    }
}
