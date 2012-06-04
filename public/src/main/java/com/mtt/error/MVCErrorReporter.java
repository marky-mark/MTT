package com.mtt.error;

import javax.validation.ConstraintViolation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MVCErrorReporter implements ErrorReporter {

    private String globalErrorMessage;

    private Map<String, String> fieldErrorMessages = new HashMap<String, String> ();

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
