package com.maitaryane.um.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private String confirmPassword;
}
