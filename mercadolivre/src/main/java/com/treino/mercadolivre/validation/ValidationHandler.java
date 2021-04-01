package com.treino.mercadolivre.validation;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {

    @InitBinder
    private void activateDirectFieldAccess(DataBinder dataBinder) {
        dataBinder.initDirectFieldAccess();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleResponseException(ResponseStatusException ex){
        ValidationDto validationDto = new ValidationDto("request body", ex.getReason(), ex.getRawStatusCode());
        return new ResponseEntity<>(validationDto,HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ValidationDto validationDto = new ValidationDto("request body", ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(validationDto,HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        List<ValidationDto> errors = buildErrorList(globalErrors,fieldErrors,status);

        return new ResponseEntity<>(errors,status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        List<ValidationDto> errors = buildErrorList(globalErrors,fieldErrors,status);

        return new ResponseEntity<>(errors,status);
    }

    public List<ValidationDto> buildErrorList(List<ObjectError> globalErrors, List<FieldError> fieldErrors,
                                              HttpStatus status){
        List<ValidationDto> errors = new ArrayList<>();

        globalErrors.forEach(
                e -> errors.add(new ValidationDto(e.getObjectName(),e.getDefaultMessage(),status.value()))
        );

        fieldErrors.forEach(
                e -> errors.add(new ValidationDto(e.getField(),e.getDefaultMessage(),status.value()))
        );

        return errors;
    }
}
