package com.maitaryane.um.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maitaryane.um.request.ProfileRequest;
import com.maitaryane.um.response.MessageResponse;
import com.maitaryane.um.service.UserProfileService;
import com.maitaryane.um.validation.ProfileImageValidation;
import com.maitaryane.um.validation.UserAccountUpdateProfileValidation;
import com.maitaryane.um.validation.ValidationErrors;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class UserAccountProfileController {

	private final UserProfileService userProfileService;
	private final UserAccountUpdateProfileValidation profileValidation;
	private final ProfileImageValidation profileImageValidation;

	@GetMapping("profile/image")

	public ResponseEntity<byte[]> getImage(Authentication authentication) {
        byte[] imageData = userProfileService.getImage(authentication);
        
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
		userProfileService.changeImage(authentication, image);

		// Return a success response
		MessageResponse response = new MessageResponse("Profile picture updated successfully");
	    return ResponseEntity.ok(response);
	}

	
	@GetMapping("profile")
	public ProfileRequest showProfile(Authentication authentication) {
		return userProfileService.showProfile(authentication);
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
		userProfileService.editProfile(authentication, profile);

		// Return a success response
		MessageResponse response = new MessageResponse("User profile updated successfully.");
		return ResponseEntity.ok(response);

	}

	@DeleteMapping("profile")
	public ResponseEntity<?> deleteProfile(Authentication authentication) {
		
		userProfileService.deleteProfile(authentication);

		// Return a success response
		MessageResponse response = new MessageResponse("User profile deleted successfully.");
		return ResponseEntity.ok(response);
	}
}
