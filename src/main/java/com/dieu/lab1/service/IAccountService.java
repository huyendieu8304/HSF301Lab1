package com.dieu.lab1.service;

import com.dieu.lab1.entity.Account;

public interface IAccountService {
    void addAccount(Account account);
    Account findByEmail(String email);
}
