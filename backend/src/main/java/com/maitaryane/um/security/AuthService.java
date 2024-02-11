package com.maitaryane.um.security;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.maitaryane.um.request.LoginRequest;
import com.maitaryane.um.response.MessageResponse;
import com.maitaryane.um.response.TokenResponse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {

	private final TokenService tokenService;
	private final AuthenticationManager authenticationManager;
	private final AuthenticatedAccountService authenticatedAccountService;
	private final TokenStorageService tokenStorageService;

	public ResponseEntity<?> login(LoginRequest loginDTO) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
		String access_token = tokenService.generateAccessToken(authentication);
		String refresh_token = tokenService.generateRefreshToken(authentication);
		tokenStorageService.save(new TokenStorage(null, access_token, refresh_token, false, LocalDateTime.now()));
		TokenResponse response = new TokenResponse(access_token, refresh_token, "Successfull Login.");
		return ResponseEntity.ok(response);
	}
	
	public ResponseEntity<?> logout(Authentication authentication) {
		String token = authenticatedAccountService.token(authentication);
		TokenStorage tokenStorage =  tokenStorageService.findByAccessToken(token);
		tokenStorage.setIsBlacklisted(true);
		tokenStorageService.save(tokenStorage);
		MessageResponse response = new MessageResponse("Successfull Logout.");
		return ResponseEntity.ok(response);
	}

	public ResponseEntity<?> relogin(Authentication authentication) {
		String oldToken = authenticatedAccountService.token(authentication);
		TokenStorage tokenStorage =  tokenStorageService.findByRefreshToken(oldToken);
		tokenStorage.setIsBlacklisted(true);
		tokenStorageService.save(tokenStorage);
		String access_token = tokenService.generateAccessToken(authentication);
		String refresh_token = tokenService.generateRefreshToken(authentication);
		tokenStorageService.save(new TokenStorage(null, access_token, refresh_token, false, LocalDateTime.now()));
		TokenResponse response = new TokenResponse(access_token, refresh_token, "Successfull Relogin.");
		return ResponseEntity.ok(response);
	}
	
}
