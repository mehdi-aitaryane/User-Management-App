package com.maitaryane.um.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class ValidationErrors {
    // New method for handling validation errors
	public static ResponseEntity<?> handleValidationErrors(BindingResult bindingResult) {
	    // Extract validation errors and build a response
	    Map<String, List<String>> errors = new HashMap<>();
	    for (ObjectError error : bindingResult.getAllErrors()) {
	        String code = error.getCode();
	        String message = error.getDefaultMessage();
	        if (!errors.containsKey(code)) {
	            errors.put(code, new ArrayList<>());
	        }
	        errors.get(code).add(message);
	    }
	    // Wrap errors inside another map with key "validation"
	    Map<String, Object> response = new HashMap<>();
	    response.put("validation", errors);
	    response.put("message", "Validation errors.");
	    // Return a response with validation errors
	    return ResponseEntity.badRequest().body(response);
	}

}
