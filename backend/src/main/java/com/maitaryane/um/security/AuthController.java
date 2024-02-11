package com.maitaryane.um.security;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maitaryane.um.request.LoginRequest;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class AuthController {
	private final AuthService service;
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginDTO) {
		return service.login(loginDTO);
	}

	@PostMapping("/logout")
	public ResponseEntity<?> logout(Authentication authentication) {
		return service.logout(authentication);
	}

	@PostMapping("/relogin")
	public ResponseEntity<?> relogin(Authentication authentication) {
		return service.relogin(authentication);
	}
}
