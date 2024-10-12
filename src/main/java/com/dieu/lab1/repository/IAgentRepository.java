package com.dieu.lab1.repository;

import com.dieu.lab1.entity.Agent;
import com.dieu.lab1.enumeration.EAgentStatus;

import java.util.List;

public interface IAgentRepository extends ICrudRepository<Agent, Integer> {
    List<Agent> findAgent(int pageSize, int pageNo, EAgentStatus status, String email, String name);
    public Long countAgent(EAgentStatus status, String email, String name);
    Agent findAgentByName(String name);
    Agent findAgentByEmail(String email);
}
