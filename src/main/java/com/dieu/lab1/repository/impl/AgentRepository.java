package com.dieu.lab1.repository.impl;

import com.dieu.lab1.entity.Agent;
import com.dieu.lab1.enumeration.EAgentStatus;
import com.dieu.lab1.repository.IAgentRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AgentRepository extends BaseRepository<Agent, Integer> implements IAgentRepository {

    public AgentRepository(SessionFactory sessionFactory) {
        super(sessionFactory, Agent.class);
    }


    @Override
    public List<Agent> findAgent(int pageSize, int pageNo, EAgentStatus status, String email, String name) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            StringBuilder hql = new StringBuilder("FROM Agent where 1 = 1");
            Map<String, Object> params = new HashMap<String, Object>();
            if (status != null && !status.equals("")) {
                hql.append(" AND LOWER(status) = :status");
                params.put("status", status);
            }
            if (email != null && !email.equals("")) {
                hql.append(" AND LOWER(email) LIKE :email");
                params.put("email", "%" + email.toLowerCase() + "%");
            }
            if (name != null && !name.equals("")) {
                hql.append(" AND LOWER(name) LIKE :name");
                params.put("name", "%" + name.toLowerCase() + "%");
            }
            hql.append(" ORDER BY registerDate ASC, name ASC");
            TypedQuery<Agent> query = session.createQuery(hql.toString(), Agent.class);
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                query.setParameter(entry.getKey(), entry.getValue());
            }
            query.setFirstResult((pageNo - 1) * pageSize);
            query.setMaxResults(pageSize);
            List<Agent> agents = query.getResultList();
            tx.commit();
            return agents;
        } catch (Exception e){
            tx.rollback();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Long countAgent(EAgentStatus status, String email, String name) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            StringBuilder hql = new StringBuilder("SELECT COUNT(*) FROM Agent where 1 = 1");
            Map<String, Object> params = new HashMap<String, Object>();
            if (status != null && !status.equals("")) {
                hql.append(" AND LOWER(status) = :status");
                params.put("status", status);
            }
            if (email != null && !email.equals("")) {
                hql.append(" AND LOWER(email) LIKE :email");
                params.put("email", "%" + email.toLowerCase() + "%");
            }
            if (name != null && !name.equals("")) {
                hql.append(" AND LOWER(name) LIKE :name");
                params.put("name", "%" + name.toLowerCase() + "%");
            }

            TypedQuery<Long> query = session.createQuery(hql.toString(), Long.class);
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                query.setParameter(entry.getKey(), entry.getValue());
            }

            Long count = query.getSingleResult();
            tx.commit();
            return count;
        } catch (Exception e){
            tx.rollback();
            e.printStackTrace();
            throw e;
        }
    }
}

