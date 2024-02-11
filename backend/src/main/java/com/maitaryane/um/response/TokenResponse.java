package com.maitaryane.um.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse{
	private String access_token;
	private String refresh_token;
	private String message;	
}
