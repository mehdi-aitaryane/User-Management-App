package com.maitaryane.um.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.maitaryane.um.entity.UserAccount;
import com.maitaryane.um.request.RegistrationRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserAccountRegistrationService {
	private final PasswordEncoder passwordEncoder;
	private final UserAccountService userAccountService;
	
	public void register(RegistrationRequest registrationDTO)
	{
		UserAccount user = new UserAccount();
		user.setFirstname(registrationDTO.getFirstname());
		user.setLastname(registrationDTO.getLastname());
		user.setUsername(registrationDTO.getUsername());
		user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
		userAccountService.createAccount(user);
	}
}
