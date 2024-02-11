package com.maitaryane.um.validation;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.maitaryane.um.repository.AccountRepository;
import com.maitaryane.um.request.RegistrationRequest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserAccountRegistrationValidation {
	
	private final AccountRepository accountRepository;
	
	public BindingResult validateRegister(RegistrationRequest registrationDTO, BindingResult bindingResult)
	{	
		bindingResult = validateFirstname(registrationDTO.getFirstname(), bindingResult);
		bindingResult = validateLastname(registrationDTO.getLastname(), bindingResult);
		bindingResult = validateUsername(registrationDTO.getUsername(), bindingResult);
		bindingResult = validatePassword(registrationDTO.getPassword(), bindingResult);
		bindingResult = validateConfirmPassword(registrationDTO.getPassword(), registrationDTO.getConfirmPassword(), bindingResult);
		
		return bindingResult;
	}
	
	private BindingResult validateFirstname(String firstname, BindingResult bindingResult)
	{
		bindingResult = FieldValidation.isBlank("firstname", firstname, "Firstname is required.",  bindingResult);
		bindingResult = FieldValidation.isLess("firstname", firstname, 2, "Firstname must not be less than 2 characters.",  bindingResult);
		bindingResult = FieldValidation.isGreater("firstname", firstname, 10, "	", bindingResult);
		bindingResult = FieldValidation.startWithLetter("firstname", firstname, "Firstname must start with a letter.",  bindingResult);
		bindingResult = FieldValidation.onlyLettersAndSpace("firstname", firstname, "Firstname must contains only letters and spaces.",  bindingResult);
		return bindingResult;
	}
	
	private BindingResult validateLastname(String lastname, BindingResult bindingResult)
	{
		bindingResult = FieldValidation.isBlank("lastname", lastname, "Lastname is required.",  bindingResult);
		bindingResult = FieldValidation.isLess("lastname", lastname, 2, "Lastname must not be less than 2 characters.",  bindingResult);
		bindingResult = FieldValidation.isGreater("lastname", lastname, 10, "Lastname must no be greater than 10 characters.", bindingResult);
		bindingResult = FieldValidation.startWithLetter("lastname", lastname, "Lastname must start with a letter.",  bindingResult);
		bindingResult = FieldValidation.onlyLettersAndSpace("lastname", lastname, "Lastname must contains only letters and spaces.",  bindingResult);
		return bindingResult;
	}

	private BindingResult validateUsername(String username, BindingResult bindingResult)
	{
		bindingResult = FieldValidation.isBlank("username", username, "Username is required.",  bindingResult);
		bindingResult = FieldValidation.isLess("username", username, 2, "Username must not be less than 2 characters.",  bindingResult);
		bindingResult = FieldValidation.isGreater("username", username, 10, "Username must no be greater than 10 characters.", bindingResult);
		bindingResult = FieldValidation.startWithLetter("username", username, "Username must start with a letter.",  bindingResult);
		bindingResult = FieldValidation.hasValidCharacters("username", username, "Username must have valid characters.", bindingResult);

		Boolean isPresent = accountRepository.findByUsername(username).isPresent();
		if(isPresent)
		{
			bindingResult.rejectValue(null, "username", "Username is taken.");
		}
		
		return bindingResult;
	}

	private BindingResult validatePassword(String password, BindingResult bindingResult)
	{
		bindingResult = FieldValidation.isBlank("password", password, "Password is required.",  bindingResult);
		bindingResult = FieldValidation.isLess("password", password, 8, "Password must not be less than 8 characters.",  bindingResult);
		bindingResult = FieldValidation.isGreater("password", password, 24, "Password must no be greater than 24 characters.", bindingResult);
		bindingResult = FieldValidation.hasDigits("password", password, "Password must have atleast one digit.",  bindingResult);
		bindingResult = FieldValidation.hasLowercase("password", password, "Password must have atleast one lowercase character.",  bindingResult);
		bindingResult = FieldValidation.hasUppercase("password", password, "Password must have atleast one uppercase character.", bindingResult);
		bindingResult = FieldValidation.hasSpecial("password", password, "Password must have atleast one special character.",  bindingResult);
		bindingResult = FieldValidation.hasNoSpace("password", password, "Password must not have any whtiespace.",  bindingResult);
		return bindingResult;
	}

	private BindingResult validateConfirmPassword(String password, String confirmPassword, BindingResult bindingResult)
	{
		bindingResult = FieldValidation.confirm("confirmPassword", password, confirmPassword, "Password is not confirmed.", bindingResult);
		return bindingResult;
	}
	
}
