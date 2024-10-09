package com.dieu.lab1.repository;

import com.dieu.lab1.entity.Agent;

public interface IAgentRepository {
    void save(Agent account);
    Agent getAccountById(int id);
    void updateAccount(Agent account);
    void deleteAccount(int id);



}
