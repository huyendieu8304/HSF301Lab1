package com.dieu.lab1.service.impl;

import com.dieu.lab1.dto.AccountDto;
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
    public AccountDto findByEmail(String email) {
        Account account = accountRepository.findByEmail(email);
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setEmail(account.getEmail());
        return accountDto;
    }

    @Override
    public boolean verifyAccount(AccountDto account) {
        Account seachedAccount = accountRepository.findByEmail(account.getEmail());
        return seachedAccount != null
                && seachedAccount.getPassword().equals(account.getPassword());
    }
}
