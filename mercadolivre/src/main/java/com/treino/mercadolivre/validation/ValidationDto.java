package com.treino.mercadolivre.validation;

public class ValidationDto {
    private String fieldError;

    private String message;

    private Integer statusCode;

    public String getFieldError() {
        return fieldError;
    }

    public String getMessage() {
        return message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public ValidationDto(String fieldError, String message, Integer statusCode) {
        this.fieldError = fieldError;
        this.message = message;
        this.statusCode = statusCode;
    }
}
