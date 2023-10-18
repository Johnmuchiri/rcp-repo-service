package com.red.pharma.controllers;

import com.red.pharma.exception.ControllerExceptionDetails;
import com.red.pharma.exception.ControllerExceptionDetailsImpl;
import com.red.pharma.exception.RequestValidationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BaseController {

    /**
     * This method handles all exceptions that have not handled by specific exception handlers
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(
            Exception exception, HttpServletRequest request) {

        ControllerExceptionDetails exceptionDetails = new ControllerExceptionDetailsImpl();

        Map<String, Object> responseBody = exceptionDetails
                .getExceptionDetails(exception, request,
                        getHttpStatus(exception));
        return new ResponseEntity<Map<String, Object>>(responseBody,
                getHttpStatus(exception));
    }

    /**
     * This method handles all request validation exceptions
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleRequestValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }

    private HttpStatus getHttpStatus(Exception exception){
        if (exception instanceof RequestValidationException){
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
