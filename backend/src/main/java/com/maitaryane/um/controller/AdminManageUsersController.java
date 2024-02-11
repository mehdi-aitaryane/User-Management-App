package com.maitaryane.um.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maitaryane.um.request.ProfileRequest;
import com.maitaryane.um.request.UserRequest;
import com.maitaryane.um.response.MessageResponse;
import com.maitaryane.um.service.UserAccountService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class AdminManageUsersController {
	private final UserAccountService service;
	
	@GetMapping("users")
	public Page<UserRequest> findByKeyword(
			@RequestParam(defaultValue = "") String keyword,
		    @PageableDefault(page = 0, size = 10) Pageable pageable) 
	{
		return service.findByKeyword(keyword, pageable);
	}

	@GetMapping("users/{username}")
	public ProfileRequest viewUserProfile( @PathVariable("username") String username) 
	{
		return service.findProfileByUsername(username);
	}

	@GetMapping("users/images/{username}")
	public byte[] viewUserPicture( @PathVariable("username") String username) 
	{
		return service.getProfileImageByUsername(username);
	}

	
	@DeleteMapping("users/{username}")
	public ResponseEntity<?> deleteUser( @PathVariable("username") String username) 
	{
		service.deleteAccount(username);
		// Return a success response
		MessageResponse response = new MessageResponse("Selected user deleted successfully.");
		return ResponseEntity.ok(response);
	}	
}
