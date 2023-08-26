package com.example.BankExample.service;

import com.example.BankExample.DTO.AgentDTO;
import com.example.BankExample.model.Agent;

import java.util.List;

public interface AgentService {
    void createAgent(AgentDTO agentDTO);
    void deleteAgentById(int id);
    AgentDTO getAgentById(int id);
    List<AgentDTO> getAllAgents();
    Agent updateAgent(Agent agent, int id);

}
