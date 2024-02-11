package com.maitaryane.um.validation;

import org.springframework.validation.BindingResult;

public class FieldValidation {

	
	public static BindingResult isBlank(String fieldName, String field, String msg, BindingResult bindingResult) {
		
		if(field == null)
		{
			field = "";
		}
		
		if(field.isBlank())
		{
			bindingResult.rejectValue(null, fieldName, msg);
		}

		return bindingResult;
	}

	public static BindingResult isLess(String fieldName, String field, int min, String msg, BindingResult bindingResult) {
		
		if(field == null)
		{
			field = "";
		}

		if(field.length() < min)
		{
			bindingResult.rejectValue(null, fieldName, msg);
		}

		return bindingResult;
	}

	public static BindingResult isGreater(String fieldName, String field, int max, String msg, BindingResult bindingResult) {
		
		if(field == null)
		{
			field = "";
		}

		if(field.length() > max)
		{
			bindingResult.rejectValue(null, fieldName, msg);
		}

		return bindingResult;
	}

	public static BindingResult hasDigits(String fieldName, String field, String msg, BindingResult bindingResult) {
		
		if(field == null)
		{
			field = "";
		}

		if(!field.matches(".*[0-9].*"))
		{
			bindingResult.rejectValue(null, fieldName, msg);
		}

		return bindingResult;
	}

	public static BindingResult hasLowercase(String fieldName, String field, String msg, BindingResult bindingResult) {
		
		if(field == null)
		{
			field = "";
		}

		if(!field.matches(".*[a-z].*"))
		{
			bindingResult.rejectValue(null, fieldName, msg);
		}

		return bindingResult;
	}

	public static BindingResult hasUppercase(String fieldName, String field, String msg, BindingResult bindingResult) {
		
		if(field == null)
		{
			field = "";
		}

		if(!field.matches(".*[A-Z].*"))
		{
			bindingResult.rejectValue(null, fieldName, msg);
		}

		return bindingResult;
	}

	public static BindingResult hasSpecial(String fieldName, String field, String msg, BindingResult bindingResult) {
		
		if(field == null)
		{
			field = "";
		}

		if(!field.matches(".*[@#$%^&+=].*"))
		{
			bindingResult.rejectValue(null, fieldName, msg);
		}

		return bindingResult;
	}

	public static BindingResult hasNoSpace(String fieldName, String field, String msg, BindingResult bindingResult) {
		
		if(field == null)
		{
			field = "";
		}

		if(!field.matches(".*\\S.*"))
		{
			bindingResult.rejectValue(null, fieldName, msg);
		}

		return bindingResult;
	}

	public static BindingResult onlyLettersAndSpace(String fieldName, String field, String msg, BindingResult bindingResult) {
		
		if(field == null)
		{
			field = "";
		}

		if(!field.matches("^[a-zA-Z ]+$"))
		{
			bindingResult.rejectValue(null, fieldName, msg);
		}

		return bindingResult;
	}

	
	public static BindingResult startWithLetter(String fieldName, String field, String msg, BindingResult bindingResult) {
		
		if(field == null)
		{
			field = "";
		}

		if(!field.matches("^[a-zA-Z].*$"))
		{
			bindingResult.rejectValue(null, fieldName, msg);
		}

		return bindingResult;
	}

	public static BindingResult hasValidCharacters(String fieldName, String field, String msg, BindingResult bindingResult) {
		
		if(field == null)
		{
			field = "";
		}

		if(!field.matches("^[a-zA-Z\\d_]+$"))
		{
			bindingResult.rejectValue(null, fieldName, msg);
		}

		return bindingResult;
	}
	
	public static BindingResult confirm(String fieldName, String field1, String field2, String msg, BindingResult bindingResult) {
		
		if(field1 == null)
		{
			field1 = "";
		}

		if(field2 == null)
		{
			field2 = "";
		}

		if(!field1.equals(field2))
		{
			bindingResult.rejectValue(null, fieldName, msg);
		}

		return bindingResult;
	}

}
