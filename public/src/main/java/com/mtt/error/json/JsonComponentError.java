package com.mtt.error.json;

public class JsonComponentError {

    private String field;

    private String message;

    public JsonComponentError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public JsonComponentError(String fieldName) {
        this.field = fieldName;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof JsonComponentError &&
            ((JsonComponentError)o).field.equals(field) );
    }
}
