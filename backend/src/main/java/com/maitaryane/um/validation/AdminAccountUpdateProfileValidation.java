package com.maitaryane.um.validation;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.maitaryane.um.request.ProfileRequest;
import com.maitaryane.um.security.AuthenticatedAdminAccountService;
import com.maitaryane.um.service.AccountService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminAccountUpdateProfileValidation {
	
	private final AccountService service;
	private final AuthenticatedAdminAccountService authenticatedAccountService;
	
	public BindingResult validateUpdateProfile(Authentication authentication, ProfileRequest profile, BindingResult bindingResult)
	{
		String username = authenticatedAccountService.subject(authentication);
		bindingResult = validateFirstname(profile.getFirstname(), bindingResult);
		bindingResult = validateLastname(profile.getLastname(), bindingResult);
		bindingResult = validateUsername(username, profile.getUsername(), bindingResult);
		
		return bindingResult;
	}
	
	private BindingResult validateFirstname(String firstname, BindingResult bindingResult)
	{
		bindingResult = FieldValidation.isBlank("firstname", firstname, "Firstname is required.",  bindingResult);
		bindingResult = FieldValidation.isLess("firstname", firstname, 2, "Firstname must not be less than 2 characters.",  bindingResult);
		bindingResult = FieldValidation.isGreater("firstname", firstname, 10, "Firstname must no be greater than 10 characters.", bindingResult);
		return bindingResult;
	}
	
	private BindingResult validateLastname(String lastname, BindingResult bindingResult)
	{
		bindingResult = FieldValidation.isBlank("lastname", lastname, "Lastname is required.",  bindingResult);
		bindingResult = FieldValidation.isLess("lastname", lastname, 2, "Lastname must not be less than 2 characters.",  bindingResult);
		bindingResult = FieldValidation.isGreater("lastname", lastname, 10, "Lastname must no be greater than 10 characters.", bindingResult);
		return bindingResult;
	}

	private BindingResult validateUsername(String oldUsername, String newUsername, BindingResult bindingResult)
	{
		bindingResult = FieldValidation.isBlank("username", newUsername, "Username is required.",  bindingResult);
		bindingResult = FieldValidation.isLess("username", newUsername, 2, "Username must not be less than 2 characters.",  bindingResult);
		bindingResult = FieldValidation.isGreater("username", newUsername, 10, "Username must no be greater than 10 characters.", bindingResult);
		bindingResult = FieldValidation.startWithLetter("username", newUsername, "Username must start with a letter.",  bindingResult);
		bindingResult = FieldValidation.hasValidCharacters("username", newUsername, "Username must have valid characters.", bindingResult);
		
		if(!oldUsername.equals(newUsername))
		{
			Boolean isPresent = service.isAccountPresentByUsername(newUsername);
			if(isPresent)
			{
				bindingResult.rejectValue(null, "username", "Username is taken.");
			}
		}
		return bindingResult;
	}
}
