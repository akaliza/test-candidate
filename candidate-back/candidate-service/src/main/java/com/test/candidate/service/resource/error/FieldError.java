package com.test.candidate.service.resource.error;

/**
 * Created by florakalisa on 9/20/15.
 */
public class FieldError{

    private String resource;
    private String field;

    private String code;
    private String rejectedValue;

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRejectedValue() {
        return rejectedValue;
    }

    public void setRejectedValue(String rejectedValue) {
        this.rejectedValue = rejectedValue;
    }
}
