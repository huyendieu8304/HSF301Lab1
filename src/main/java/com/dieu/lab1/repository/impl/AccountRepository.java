package com.dieu.lab1.repository.impl;

import com.dieu.lab1.entity.Account;
import com.dieu.lab1.repository.IAccountRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.TypedQuery;

public class AccountRepository extends BaseRepository implements IAccountRepository {

    public AccountRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void save(Account account) {

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(account);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }

    public Account getAccountById(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try{
            Account account = (Account) session.get(Account.class, id);
            tx.commit();
            return account;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateAccount(Account account) {

    }

    @Override
    public void deleteAccount(int id) {

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
        }
        return null;
    }

}
