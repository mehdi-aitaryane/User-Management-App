package com.maitaryane.um.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.maitaryane.um.response.MessageResponse;

@ControllerAdvice
public class SecurityExceptionHandler extends ResponseEntityExceptionHandler{



    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public ResponseEntity<?> handleAuthenticationException(AuthenticationException ex) {
        MessageResponse response = new MessageResponse("Failed Login.");
        
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

}
