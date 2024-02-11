package com.maitaryane.um.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.maitaryane.um.entity.AdminAccount;
import com.maitaryane.um.request.PasswordRequest;
import com.maitaryane.um.security.AuthenticatedAdminAccountService;
import com.maitaryane.um.security.TokenStorage;
import com.maitaryane.um.security.TokenStorageService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminAccountUpdatePasswordService{

	private final PasswordEncoder passwordEncoder;
	private final AdminAccountService accountService;
	private final TokenStorageService blacklistService;
	private final AuthenticatedAdminAccountService authenticatedAccountService;

	public void changePassword(Authentication authentication, PasswordRequest password) {
        AdminAccount account = authenticatedAccountService.get(authentication);
        account.setPassword(passwordEncoder.encode(password.getNewPassword()));
		accountService.updateAccount(account);
		
        String accessToken = authenticatedAccountService.token(authentication);
		TokenStorage tokenStorage =  blacklistService.findByAccessToken(accessToken);
		tokenStorage.setIsBlacklisted(true);
        blacklistService.save(tokenStorage);
	}

}
