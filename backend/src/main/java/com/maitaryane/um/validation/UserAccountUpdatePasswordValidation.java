package com.maitaryane.um.validation;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.maitaryane.um.entity.Account;
import com.maitaryane.um.request.PasswordRequest;
import com.maitaryane.um.security.AuthenticatedUserAccountService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserAccountUpdatePasswordValidation {
	
	private final AuthenticatedUserAccountService authenticatedAccountService;
	private final PasswordEncoder passwordEncoder;
	
	public BindingResult validateUpdatePassword(Authentication authentication, PasswordRequest passwordDTO, BindingResult bindingResult)
	{	
        Account account = authenticatedAccountService.get(authentication);
        bindingResult = validateOldPassword(account.getPassword(), passwordDTO.getOldPassword(), bindingResult);
        bindingResult = validateNewPassword(account.getPassword(), passwordDTO.getNewPassword(), bindingResult);
        bindingResult = validateConfirmPassword(passwordDTO.getNewPassword(), passwordDTO.getConfirmPassword(), bindingResult);
        
		return bindingResult;
	}
	
	private BindingResult validateNewPassword(String encodedPassword, String password, BindingResult bindingResult)
	{
		bindingResult = FieldValidation.isBlank("newPassword", password, "Password is required.",  bindingResult);
		bindingResult = FieldValidation.isLess("newPassword", password, 8, "Password must not be less than 8 characters.",  bindingResult);
		bindingResult = FieldValidation.isGreater("newPassword", password, 24, "Password must no be greater than 24 characters.", bindingResult);
		bindingResult = FieldValidation.hasDigits("newPassword", password, "Password must have atleast one digit.",  bindingResult);
		bindingResult = FieldValidation.hasLowercase("newPassword", password, "Password must have atleast one lowercase character.",  bindingResult);
		bindingResult = FieldValidation.hasUppercase("newPassword", password, "Password must have atleast one uppercase character.", bindingResult);
		bindingResult = FieldValidation.hasSpecial("newPassword", password, "Password must have atleast one special character.",  bindingResult);
		bindingResult = FieldValidation.hasNoSpace("newPassword", password, "Password must not have any whitespace.",  bindingResult);
		
		if(passwordEncoder.matches(password, encodedPassword)){
			bindingResult.rejectValue(null, "newPassword", "Password is not changed.");
		}
		
		return bindingResult;
	}

	private BindingResult validateOldPassword(String encodedPassword, String password, BindingResult bindingResult)
	{
		if(!passwordEncoder.matches(password, encodedPassword)){
			bindingResult.rejectValue(null, "oldPassword", "Password is not correct.");
		}

		return bindingResult;
	}
	
	private BindingResult validateConfirmPassword(String password, String confirmPassword, BindingResult bindingResult)
	{
		bindingResult = FieldValidation.confirm("confirmPassword", password, confirmPassword, "Password is not confirmed.", bindingResult);
		return bindingResult;
	}


}
