package com.dieu.lab1.service.impl;

import com.dieu.lab1.dto.AgentDto;
import com.dieu.lab1.entity.Agent;
import com.dieu.lab1.enumeration.EAgentStatus;
import com.dieu.lab1.repository.IAgentRepository;
import com.dieu.lab1.repository.impl.AgentRepository;
import com.dieu.lab1.service.IAgentService;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<AgentDto> searchAgent(int pageSize, int pageNo, String status, String email, String name) {

        EAgentStatus agentStatus = null;
        if (status != null && status != "") {
            agentStatus = EAgentStatus.valueOf(status);
        }

        List<Agent> agentList = agentRepository.findAgent(pageSize, pageNo, agentStatus, email, name);

        return agentList.stream().map(agent -> {
            return convertToAgentDto(agent);
        }).toList();
    }

    @Override
    public int getNoPages(int pageSize, String status, String email, String name) {
        EAgentStatus agentStatus = null;
        if (status != null && status != "") {
            agentStatus = EAgentStatus.valueOf(status);
        }

        Long numberOfAgents = agentRepository.countAgent(agentStatus, email, name);
        int noOfPages = (int) Math.ceil((double)numberOfAgents / pageSize);
        return noOfPages;
    }

    @Override
    public AgentDto getAgent(int id) {
        Agent agent =  agentRepository.findById(id);
        AgentDto agentDto = new AgentDto();
        return convertToAgentDto(agent);
    }

    @Override
    public boolean isAgentNameExist(String name, int agentId) {
        Agent agent = agentRepository.findAgentByName(name);
        return agent != null && !agent.getId().equals(agentId);
    }

    @Override
    public boolean isAgentEmailExist(String email,  int agentId) {
        Agent agent = agentRepository.findAgentByName(email);
        return agent != null && !agent.getId().equals(agentId);
    }

    @Override
    public boolean updateAgent(Agent agent) {
        return agentRepository.update(agent);
    }

    public AgentDto convertToAgentDto(Agent agent) {
        AgentDto agentDto = new AgentDto();
        agentDto.setId(agent.getId());
        agentDto.setName(agent.getName());
        agentDto.setEmail(agent.getEmail());
        agentDto.setStatus(agent.getStatus().toString());
        agentDto.setAddress(agent.getAddress());
        agentDto.setRegisterDate(agent.getRegisterDate());
        agentDto.setAccountBalance(agent.getAccountBalance());
        return agentDto;
    }
}
