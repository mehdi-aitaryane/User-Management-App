package com.maitaryane.um.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maitaryane.um.request.PasswordRequest;
import com.maitaryane.um.response.MessageResponse;
import com.maitaryane.um.service.UserAccountUpdatePasswordService;
import com.maitaryane.um.validation.UserAccountUpdatePasswordValidation;
import com.maitaryane.um.validation.ValidationErrors;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class UserAccountUpdatePasswordController {
	private final UserAccountUpdatePasswordService service;
	private final UserAccountUpdatePasswordValidation validation;
	
	@PutMapping("password")
	public ResponseEntity<?> changePassword(Authentication authentication, @RequestBody PasswordRequest password, BindingResult bindingResult) {
		
		// validate user update password form
		bindingResult = this.validation.validateUpdatePassword(authentication, password, bindingResult);
				
		// handling validation errors
		
        if (bindingResult.hasErrors()) {
            return ValidationErrors.handleValidationErrors(bindingResult);
        }
		
	    // registration business logic here
		service.changePassword(authentication, password);
		
	    // Return a success response
		MessageResponse response = new MessageResponse("User password updated successfully");
	    return ResponseEntity.ok(response);

	}
}
