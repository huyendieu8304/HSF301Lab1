package com.dieu.lab1.repository;

import com.dieu.lab1.entity.Account;

public interface IAccountRepository {
    void save(Account account);
    Account getAccountById(int id);
    void updateAccount(Account account);
    void deleteAccount(int id);

    Account findByEmail(String email);
}
