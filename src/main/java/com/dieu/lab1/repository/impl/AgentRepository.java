package com.dieu.lab1.repository.impl;

import com.dieu.lab1.entity.Agent;
import com.dieu.lab1.repository.IAgentRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AgentRepository extends BaseRepository<Agent, Integer> implements IAgentRepository {

    public AgentRepository(SessionFactory sessionFactory) {
        super(sessionFactory, Agent.class);
    }



}
//    @Override
//    public boolean save(Agent account) {
//        Session session = sessionFactory.openSession();
//        Transaction tx = session.beginTransaction();
//        try {
//            session.save(account);
//            tx.commit();
//            return true;
//        } catch (Exception e) {
//            tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return false;
//    }
