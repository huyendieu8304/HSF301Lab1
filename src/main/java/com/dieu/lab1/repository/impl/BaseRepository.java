package com.dieu.lab1.repository.impl;

import org.hibernate.SessionFactory;

public class BaseRepository {
    protected SessionFactory sessionFactory;

    public BaseRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
