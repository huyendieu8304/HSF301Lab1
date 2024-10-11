package com.dieu.lab1.repository.impl;

import com.dieu.lab1.entity.Account;
import com.dieu.lab1.repository.IAccountRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.TypedQuery;

public class AccountRepository extends BaseRepository<Account, Integer> implements IAccountRepository {

    public AccountRepository(SessionFactory sessionFactory) {
        super(sessionFactory, Account.class);
    }


    public Account findByEmail(String email) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            TypedQuery<Account> query = session.createQuery("from Account where email = :email", Account.class);
            query.setParameter("email", email);
            Account account = query.getSingleResult();
            tx.commit();
            return account;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            throw e;
        }
    }

}
