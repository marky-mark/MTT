package com.mtt.error.json;

import org.springframework.validation.BindingResult;

/**
 * Root model for JSON validation responses.
 */
public final class JsonValidationResponse {

    private JsonErrorReporter errorReporter;

    public JsonValidationResponse(BindingResult bindingResult) {
        errorReporter = new JsonErrorReporter(bindingResult);
    }

    public JsonErrorReporter getErrorReporter() {
        return errorReporter;
    }
}
