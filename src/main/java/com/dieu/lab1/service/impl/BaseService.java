package com.dieu.lab1.service.impl;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class BaseService {

    protected SessionFactory sessionFactory;

    public BaseService() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

}
