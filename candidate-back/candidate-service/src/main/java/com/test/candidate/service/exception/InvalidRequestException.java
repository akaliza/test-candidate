package com.test.candidate.service.exception;

import com.test.candidate.service.resource.error.FieldError;

/**
 * Created by florakalisa on 9/20/15.
 */
public class InvalidRequestException extends Exception {

    private String type;
    private String resource;
    private FieldError[] field;

    public InvalidRequestException(String type, String resource, FieldError...field ) {
        this.type = type;
        this.resource = resource;
        this.field = field;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public FieldError[] getField() {
        return field;
    }

    public void setField(FieldError[] field) {
        this.field = field;
    }
}
