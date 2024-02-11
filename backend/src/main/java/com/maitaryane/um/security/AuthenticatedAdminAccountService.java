package com.maitaryane.um.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.maitaryane.um.entity.AdminAccount;
import com.maitaryane.um.service.AdminAccountService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthenticatedAdminAccountService{
	
	private final AdminAccountService service;
	
	public AdminAccount get(Authentication authentication) {
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
