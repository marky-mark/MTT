package com.mtt.functions;

import com.mtt.error.ErrorReporter;

public final class ErrorFunctions {

    public static Boolean hasError(ErrorReporter errorReporter, String fieldName) {
        return errorReporter!= null && errorReporter.getFieldError(fieldName) != null;
    }

    public static String getError(ErrorReporter errorReporter, String fieldName) {
        return errorReporter.getFieldError(fieldName);
    }

    public static Boolean hasGlobalError(ErrorReporter errorReporter) {
        return errorReporter!= null && errorReporter.getGlobalError() != null;
    }

    public static String getGlobalError(ErrorReporter errorReporter) {
        return errorReporter.getGlobalError();
    }
}
