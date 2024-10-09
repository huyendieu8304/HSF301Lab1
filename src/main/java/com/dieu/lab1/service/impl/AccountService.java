package com.dieu.lab1.service.impl;

import com.dieu.lab1.entity.Account;
import com.dieu.lab1.repository.IAccountRepository;
import com.dieu.lab1.repository.impl.AccountRepository;
import com.dieu.lab1.service.IAccountService;

public class AccountService extends BaseService implements IAccountService {

    private IAccountRepository accountRepository;

    public AccountService() {
        super();
        accountRepository = new AccountRepository(sessionFactory);
    }

    @Override
    public void addAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public Account findByEmail(String email) {
        System.out.println("account serrvice is called");
        return accountRepository.findByEmail(email);
    }
}
