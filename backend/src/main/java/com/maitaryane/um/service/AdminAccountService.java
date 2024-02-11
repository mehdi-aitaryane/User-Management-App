package com.maitaryane.um.service;

import org.springframework.stereotype.Service;

import com.maitaryane.um.entity.AdminAccount;
import com.maitaryane.um.repository.AdminAccountRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminAccountService{

    private final AdminAccountRepository adminAccountRepository;

    public AdminAccount createAccount(AdminAccount adminAccount) {
        return adminAccountRepository.save(adminAccount);
    }

    public AdminAccount findAccountByUsername(String username) {
        return adminAccountRepository.findByUsername(username).orElseThrow();
    }

    public AdminAccount updateAccount(AdminAccount adminAccount) {
        return adminAccountRepository.save(adminAccount);
    }

    public void deleteAccount(String username) {
        adminAccountRepository.findByUsername(username).ifPresent(adminAccountRepository::delete);
    }
}
