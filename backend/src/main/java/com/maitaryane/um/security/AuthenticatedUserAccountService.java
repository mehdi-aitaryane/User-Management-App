package com.maitaryane.um.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.maitaryane.um.entity.UserAccount;
import com.maitaryane.um.service.UserAccountService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthenticatedUserAccountService{
	
	private final UserAccountService service;

	public UserAccount get(Authentication authentication) {
		String username = subject(authentication);
		return service.findAccountByUsername(username);
	}

	public String token(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        Jwt jwt = (Jwt) principal;
		return jwt.getTokenValue();
	}

	public String subject(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        Jwt jwt = (Jwt) principal;
		return jwt.getSubject();
	}

}
