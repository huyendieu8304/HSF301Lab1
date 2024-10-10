package com.dieu.lab1.service.impl;

import com.dieu.lab1.entity.Agent;
import com.dieu.lab1.repository.IAgentRepository;
import com.dieu.lab1.repository.impl.AgentRepository;
import com.dieu.lab1.service.IAgentService;

public class AgentService extends BaseService implements IAgentService {
    private IAgentRepository agentRepository;

    public AgentService() {
        super();
        agentRepository = new AgentRepository(sessionFactory);
    }

    @Override
    public boolean createAgent(Agent agent) {
        return agentRepository.save(agent);
    }
}
