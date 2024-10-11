package com.dieu.lab1.service;

import com.dieu.lab1.dto.AgentDto;
import com.dieu.lab1.entity.Agent;

import java.util.List;

public interface IAgentService {

    boolean createAgent(Agent agent);

    List<AgentDto> searchAgent(int pageSize, int pageNo, String status, String email, String name);

    int getNoPages (int pageSize, String status, String email, String name);

    AgentDto getAgent(int id);

    boolean isAgentNameExist (String name);
    boolean isAgentEmailExist (String email);

    boolean updateAgent(Agent agent);
}
