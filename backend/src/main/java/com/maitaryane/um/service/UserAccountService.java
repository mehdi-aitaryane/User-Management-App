package com.maitaryane.um.service;

import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.maitaryane.um.entity.UserAccount;
import com.maitaryane.um.repository.UserAccountRepository;
import com.maitaryane.um.request.ProfileRequest;
import com.maitaryane.um.request.UserRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    public UserAccount createAccount(UserAccount userAccount) {
        return userAccountRepository.save(userAccount);
    }

    public UserAccount findAccountByUsername(String username) {
        return userAccountRepository.findByUsername(username).orElseThrow();
    }

    public UserAccount updateAccount(UserAccount userAccount) {
        return userAccountRepository.save(userAccount);
    }

    public void deleteAccount(String username) {
        UserAccount account = findAccountByUsername(username);
        userAccountRepository.delete(account);
    }
    
    public Page<UserRequest> findByKeyword(String keyword, Pageable pageable)
    {
    	return userAccountRepository.findByKeyword(keyword, pageable).map(new Function<UserAccount, UserRequest>() {
    	    @Override
    	    public UserRequest apply(UserAccount entity) {
    	    	UserRequest dto = new UserRequest();
    	    	dto.setFirstname(entity.getFirstname());
    	    	dto.setLastname(entity.getLastname());
    	    	dto.setUsername(entity.getUsername());
    	        return dto;
    	    }
    	});
    }

    public ProfileRequest findProfileByUsername(String username)
    {
    	UserAccount account = findAccountByUsername(username);
    	return new ProfileRequest(account.getFirstname(), account.getLastname(), account.getUsername(), "USER");
    } 

    public byte[] getProfileImageByUsername(String username)
    {
    	UserAccount account = findAccountByUsername(username);
    	return account.getImage();
    } 

}
