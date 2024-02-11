package com.maitaryane.um.validation;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProfileImageValidation {
	
	
	public BindingResult validateProfileImage(byte[] image, BindingResult bindingResult)
	{
		bindingResult = validateImageSize(image, bindingResult);		
		return bindingResult;
	}
	
	private BindingResult validateImageSize(byte[] image, BindingResult bindingResult)
	{
		
		if(image.length > 10000000)
		{
			bindingResult.rejectValue(null, "image", "Image size must not be bigger than 10 MB.");
		}

		return bindingResult;
	}	
}
