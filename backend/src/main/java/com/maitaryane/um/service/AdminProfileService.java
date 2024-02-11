package com.maitaryane.um.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.maitaryane.um.entity.AdminAccount;
import com.maitaryane.um.request.ProfileRequest;
import com.maitaryane.um.security.AuthenticatedAdminAccountService;
import com.maitaryane.um.security.TokenStorage;
import com.maitaryane.um.security.TokenStorageService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminProfileService {
	
	private final AdminAccountService accountService;
	private final TokenStorageService blacklistService;
	private final AuthenticatedAdminAccountService authenticatedAccountService;

	public byte[] getImage(Authentication authentication){
        AdminAccount account = authenticatedAccountService.get(authentication);
	    return account.getImage();
	}

	public void changeImage(Authentication authentication,  byte[] image) {
        AdminAccount account = authenticatedAccountService.get(authentication);
        account.setImage(image);
        accountService.updateAccount(account);
	}

	public ProfileRequest showProfile(Authentication authentication) {
        AdminAccount account = authenticatedAccountService.get(authentication);
        ProfileRequest profileDTO = new ProfileRequest();
        profileDTO.setFirstname(account.getFirstname());
        profileDTO.setLastname(account.getLastname());
        profileDTO.setUsername(account.getUsername());
        profileDTO.setType("ADMIN");
        return profileDTO;
	}

	public ProfileRequest editProfile(Authentication authentication, ProfileRequest profile) {
        AdminAccount account = authenticatedAccountService.get(authentication);
		Boolean isUsernameChanged = !profile.getUsername().equals(account.getUsername());
        account.setFirstname(profile.getFirstname());
        account.setLastname(profile.getLastname());
        account.setUsername(profile.getUsername());
        accountService.updateAccount(account);
        profile.setType("ADMIN");
        if(isUsernameChanged)
        {
            String accessToken = authenticatedAccountService.token(authentication);
    		TokenStorage tokenStorage =  blacklistService.findByAccessToken(accessToken);
    		tokenStorage.setIsBlacklisted(true);
            blacklistService.save(tokenStorage);
        }
        return profile;
	}

	public void deleteProfile(Authentication authentication) {
        AdminAccount account = authenticatedAccountService.get(authentication);
        accountService.deleteAccount(account.getUsername());
        
        String accessToken = authenticatedAccountService.token(authentication);
		TokenStorage tokenStorage =  blacklistService.findByAccessToken(accessToken);
		tokenStorage.setIsBlacklisted(true);
        blacklistService.save(tokenStorage);
	}
}
