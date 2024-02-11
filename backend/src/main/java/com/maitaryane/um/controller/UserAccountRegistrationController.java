package com.maitaryane.um.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maitaryane.um.request.RegistrationRequest;
import com.maitaryane.um.response.MessageResponse;
import com.maitaryane.um.service.UserAccountRegistrationService;
import com.maitaryane.um.validation.UserAccountRegistrationValidation;
import com.maitaryane.um.validation.ValidationErrors;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class UserAccountRegistrationController {
	
	private final UserAccountRegistrationService registrationService;
	private final UserAccountRegistrationValidation registrationValidator;

	@PostMapping("register")
	public ResponseEntity<?> register( @RequestBody RegistrationRequest registrationDTO, BindingResult bindingResult) {
		
		// validate user registration form
		
		bindingResult = registrationValidator.validateRegister(registrationDTO, bindingResult);
		
		// handling validation errors
		
        if (bindingResult.hasErrors()) {
            return ValidationErrors.handleValidationErrors(bindingResult);
        }
		
	    // registration business logic here
	    registrationService.register(registrationDTO);

	    // Return a success response
		MessageResponse response = new MessageResponse("User registered successfully.");
		return ResponseEntity.ok(response);
	}
	
 }
