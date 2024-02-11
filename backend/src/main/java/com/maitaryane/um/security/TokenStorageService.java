package com.maitaryane.um.security;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TokenStorageService {
	private TokenStorageRepository repository;
	
	public void save(TokenStorage tokens)
	{
		repository.save(tokens);
	}
	
	public TokenStorage findByAccessToken(String accessToken)
	{
		return repository.findByAccessToken(accessToken).get();
	}


	public TokenStorage findByRefreshToken(String refreshToken)
	{
		return repository.findByRefreshToken(refreshToken).get();
	}
	
    public void deleteByDateBefore(LocalDateTime date){
    	repository.deleteByDateBefore(date);
    }
    
    public Boolean isTokenBlacklisted(String token)
    {
    	return repository.findByToken(token).get().getIsBlacklisted();
    }

}
