package com.dieu.lab1.repository.impl;

import com.dieu.lab1.repository.ICrudRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class BaseRepository<T, K> implements ICrudRepository<T, K> {
    protected SessionFactory sessionFactory;
    private final Class<T> entityClass;

    public BaseRepository(SessionFactory sessionFactory, Class<T> entityClass) {
        this.sessionFactory = sessionFactory;
        this.entityClass = entityClass;
    }

    @Override
    public boolean save (T entity){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(entity);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public T findById(K id){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            T entity = session.find(entityClass, id);
            tx.commit();
            return entity;
        } catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(T entity){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.update(entity);
            tx.commit();
            return true;
        } catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void delete(T entity){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.delete(entity);
            tx.commit();
        } catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }
    }

}
