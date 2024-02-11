package com.maitaryane.um.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.maitaryane.um.entity.Account;
import com.maitaryane.um.entity.AdminAccount;
import com.maitaryane.um.entity.UserAccount;
import com.maitaryane.um.repository.AccountRepository;

import jakarta.transaction.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    public CustomUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        
  
        return convertAccount(account);
    }
    
    private UserDetails convertAccount(Account account)
    {
    	String role = null;
    	if(account instanceof UserAccount)
    	{
    		role = "USER";
    	}

    	if(account instanceof AdminAccount)
    	{
    		role = "ADMIN";
    	}
    	return User.builder()
        .username(account.getUsername())
        .password(account.getPassword())
        .roles(role)
        .build();
    	
    }
}
