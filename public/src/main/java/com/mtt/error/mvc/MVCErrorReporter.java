package com.mtt.error.mvc;

import com.mtt.error.ErrorReporter;
import com.mtt.error.ReportableErrors;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MVCErrorReporter implements ErrorReporter {

    private String globalErrorMessage;

    private Map<String, String> fieldErrorMessages = new HashMap<String, String>();

    public MVCErrorReporter(ReportableErrors errors) {
        errors.report(this);
    }

    public MVCErrorReporter(Set<? extends ConstraintViolation<?>> violations) {
        for (ConstraintViolation<?> violation : violations) {
            fieldError(violation.getPropertyPath().toString(), violation.getMessage());
        }
    }

    public MVCErrorReporter(Set<? extends ConstraintViolation<?>> violations, Long id) {
        for (ConstraintViolation<?> violation : violations) {
            fieldError(violation.getPropertyPath().toString()+ "_" + id, violation.getMessage());
        }
    }

    public MVCErrorReporter(BindingResult bindingResult) {
        for (FieldError error : bindingResult.getFieldErrors()) {
            fieldError(error.getField(), error.getDefaultMessage());
        }

        if (bindingResult.hasGlobalErrors()) {
            globalError(bindingResult.getGlobalError().getDefaultMessage());
        }
    }

    @Override
    public void fieldError(String fieldName, String fieldErrorMessage) {
        fieldErrorMessages.put(fieldName, fieldErrorMessage);
    }

    @Override
    public String getFieldError(String fieldName) {
        return fieldErrorMessages.get(fieldName);
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
