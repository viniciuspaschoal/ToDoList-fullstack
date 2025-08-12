package com.vinicius.ToDoList_fullstack.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private final int status;
    private final String message;
    private String stackTrace;
    private List<ValidationError> errors;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }

    public void setErrors(List<ValidationError> errors) {
        this.errors = errors;
    }

    public void addValidationError(String field, String message){
        if (errors == null){
            errors = new ArrayList<>();
        }

        errors.add(new ValidationError(field, message));
    }

    private static class ValidationError{
        private final String field;
        private final String message;

        private ValidationError(String field, String message) {
            this.field = field;
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public String getField() {
            return field;
        }
    }
}
