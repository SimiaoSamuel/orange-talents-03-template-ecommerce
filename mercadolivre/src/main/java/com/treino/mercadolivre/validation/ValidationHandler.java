package com.treino.mercadolivre.validation;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ValidationDto> errors = new ArrayList<>();

        globalErrors.forEach(
                e -> errors.add(new ValidationDto(e.getObjectName(),e.getDefaultMessage(),status.value()))
        );

        fieldErrors.forEach(
                e -> errors.add(new ValidationDto(e.getField(),e.getDefaultMessage(),status.value()))
        );

        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }
}
