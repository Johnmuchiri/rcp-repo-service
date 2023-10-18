package com.red.pharma.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class ControllerExceptionDetailsImpl implements ControllerExceptionDetails {

    public static final String ERROR = "error";
    public static final String MESSAGE = "message";
    public static final String TIMESTAMP = "timestamp";
    public static final String STATUS = "status";
    public static final String EXCEPTION = "exception";
    public static final String PATH = "path";

    @Override
    public Map<String, Object> getExceptionDetails(Exception exception, HttpServletRequest httpRequest, HttpStatus httpStatus) {
        Map<String, Object> exceptionDetails = new LinkedHashMap<String, Object>();
        exceptionDetails.put(TIMESTAMP, new Date());
        addExceptionHttpStatus(exceptionDetails, httpStatus);
        addExceptionMessage(exceptionDetails, exception);
        addRequestPath(exceptionDetails, httpRequest);
        return exceptionDetails;
    }

    private void addExceptionMessage(Map<String, Object> exceptionDetails,
                                    Exception exception) {
        exceptionDetails.put(EXCEPTION, exception.getClass().getName());
        exceptionDetails.put(MESSAGE, exception.getMessage());
    }

    private void addRequestPath(Map<String, Object> exceptionDetails,
                         HttpServletRequest httpRequest) {
        exceptionDetails.put(PATH, httpRequest.getServletPath());
    }

    private void addExceptionHttpStatus(Map<String, Object> exceptionDetails,
                               HttpStatus httpStatus) {
        exceptionDetails.put(STATUS, httpStatus.value());
        exceptionDetails.put(ERROR, httpStatus.getReasonPhrase());
    }
}
