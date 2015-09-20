package com.test.candidate.service.resource.error;

import java.util.List;

/**
 * Created by florakalisa on 9/20/15.
 */
public class ErrorInfo {
    private String type;
    private String message;

    private List<FieldError> fieldErrors;

    public ErrorInfo(String type, String message, List<FieldError> fieldErrors) {
        this.type = type;
        this.message = message;
        this.fieldErrors = fieldErrors;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}




