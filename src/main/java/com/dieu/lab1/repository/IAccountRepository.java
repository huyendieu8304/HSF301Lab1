package com.dieu.lab1.repository;

import com.dieu.lab1.entity.Account;

public interface IAccountRepository extends ICrudRepository<Account, Integer> {

    Account findByEmail(String email);
}
