package com.red.pharma.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;

import java.util.Map;

public interface ControllerExceptionDetails {
    Map<String, Object> getExceptionDetails(Exception exception,
                                            HttpServletRequest httpRequest, HttpStatus httpStatus);
}
