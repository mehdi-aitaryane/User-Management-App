package com.maitaryane.um.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.maitaryane.um.response.MessageResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseEntity<?> handleBadRequestException(HttpMessageNotReadableException ex) {
        MessageResponse response = new MessageResponse("Bad Request.");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NoResourceFoundException.class, NoSuchElementException.class})
    @ResponseBody
    public ResponseEntity<?> handleNoResourceFoundException(Exception ex) {
        MessageResponse response = new MessageResponse("Not Found.");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
