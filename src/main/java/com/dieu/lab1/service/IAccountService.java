package com.dieu.lab1.service;

import com.dieu.lab1.dto.AccountDto;
import com.dieu.lab1.entity.Account;

public interface IAccountService {
    void addAccount(Account account);
    AccountDto findByEmail(String email);
    boolean verifyAccount(AccountDto account);
}
