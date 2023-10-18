package com.red.pharma.auth;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

public class AuthenticationService {
    private static final String AUTH_TOKEN_HEADER_NAME = "X-API-KEY";

    public static Authentication getAuthentication(HttpServletRequest request, String auth_key) {
        String apiKey = request.getHeader(AUTH_TOKEN_HEADER_NAME);
        if ((apiKey == null || !apiKey.equals(auth_key)) && !isActuatorRequest(request) && !isSwaggerRequest(request) &&!isH2ConsoleRequest(request)) {
            throw new BadCredentialsException("Invalid API Key");
        }
        return new ApiKeyAuthentication(apiKey, AuthorityUtils.NO_AUTHORITIES);
    }

    private static boolean isActuatorRequest(HttpServletRequest request){
        String path = request.getRequestURI().substring(request.getContextPath().length());
        return path.contains("actuator");
    }

    private static boolean isSwaggerRequest(HttpServletRequest request){
        String path = request.getRequestURI().substring(request.getContextPath().length());
        return path.contains("swagger") || path.contains("/v3/api-docs");
    }

    private static boolean isH2ConsoleRequest(HttpServletRequest request){
        String path = request.getRequestURI().substring(request.getContextPath().length());
        return path.contains("h2-console");
    }
}
