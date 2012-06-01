package com.mtt.error;

public interface ErrorReporter {

    void fieldError(String fieldName, String fieldErrorMessage);

    String getFieldError(String fieldName);

    void globalError(String globalErrorMessage);

    String getGlobalError();
}
