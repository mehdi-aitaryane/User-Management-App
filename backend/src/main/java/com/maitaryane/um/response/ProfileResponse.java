package com.maitaryane.um.response;

import com.maitaryane.um.request.ProfileRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponse {
	ProfileRequest profile;
	String message;
}
