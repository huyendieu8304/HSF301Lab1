package com.dieu.lab1.service.impl;

import com.dieu.lab1.HibernateUtil;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class BaseService {

    protected SessionFactory sessionFactory;

    public BaseService() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

}
