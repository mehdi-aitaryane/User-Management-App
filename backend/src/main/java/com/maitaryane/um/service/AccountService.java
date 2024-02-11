package com.maitaryane.um.service;

import org.springframework.stereotype.Service;

import com.maitaryane.um.entity.Account;
import com.maitaryane.um.repository.AccountRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountService {
	
    private final AccountRepository accountRepository;
    
    public Account findAccountByUsername(String username) {
        return accountRepository.findByUsername(username).orElseThrow();
    }

    public Boolean isAccountPresentByUsername(String username) {
        return accountRepository.findByUsername(username).isPresent();
    }


}
