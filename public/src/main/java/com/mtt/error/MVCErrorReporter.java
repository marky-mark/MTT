package com.mtt.error;

import java.util.HashMap;
import java.util.Map;

public class MVCErrorReporter implements ErrorReporter {

    private String globalErrorMessage;

    private Map<String, String> fieldErrorMessages = new HashMap<String, String> ();

    public MVCErrorReporter(ReportableErrors errors) {
        errors.report(this);
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
