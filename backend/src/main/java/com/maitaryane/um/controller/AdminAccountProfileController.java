package com.maitaryane.um.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maitaryane.um.request.ProfileRequest;
import com.maitaryane.um.response.MessageResponse;
import com.maitaryane.um.service.AdminProfileService;
import com.maitaryane.um.validation.AdminAccountUpdateProfileValidation;
import com.maitaryane.um.validation.ProfileImageValidation;
import com.maitaryane.um.validation.ValidationErrors;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class AdminAccountProfileController {

	private final AdminProfileService adminProfileService;
	private final AdminAccountUpdateProfileValidation profileValidation;
	private final ProfileImageValidation profileImageValidation;
	
	@GetMapping("profile")
	public ProfileRequest showProfile(Authentication authentication) {
		return adminProfileService.showProfile(authentication);
	}
	
	@GetMapping("profile/image")
	public ResponseEntity<byte[]> getImage(Authentication authentication) {
        byte[] imageData = adminProfileService.getImage(authentication);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG); // Set the Content-Type to image/png
        
        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
	}

	@PutMapping("profile/image")
	public ResponseEntity<?> changeImage(Authentication authentication, @RequestBody byte[] image,
			BindingResult bindingResult) {
		// validate user registration form
		bindingResult = profileImageValidation.validateProfileImage(image, bindingResult);
		// handling validation errors
		System.out.println(bindingResult.hasErrors());
		
		if (bindingResult.hasErrors()) {
			return ValidationErrors.handleValidationErrors(bindingResult);
		}

		// registration business logic here
		adminProfileService.changeImage(authentication, image);

		// Return a success response
		MessageResponse response = new MessageResponse("Profile picture image updated successfully");
	    return ResponseEntity.ok(response);
	}


	@PutMapping("profile")
	public ResponseEntity<?> editProfile(Authentication authentication, @RequestBody ProfileRequest profile,
			BindingResult bindingResult) {
		// validate user registration form

		bindingResult = profileValidation.validateUpdateProfile(authentication, profile, bindingResult);

		// handling validation errors

		if (bindingResult.hasErrors()) {
			return ValidationErrors.handleValidationErrors(bindingResult);
		}

		// registration business logic here
		adminProfileService.editProfile(authentication, profile);

		// Return a success response
		MessageResponse response = new MessageResponse("Admin profile updated successfully");
	    return ResponseEntity.ok(response);
	}
}
