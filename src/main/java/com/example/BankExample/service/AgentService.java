package com.example.BankExample.service;

import com.example.BankExample.DTO.AgentDTO;
import com.example.BankExample.DTO.UserDTO;
import com.example.BankExample.model.Agent;
import com.example.BankExample.model.User;

import java.util.List;

public interface AgentService {
    void createAgent(AgentDTO agentDTO);
    void deleteAgentById(int id);
    AgentDTO getAgentById(int id);
    List<AgentDTO> getAllAgents();
    Agent updateAgent(Agent agent, int id);
    void addUserToAgent(int agentId,int userId);
    List<UserDTO> getUserList(int agentId);
    void depositMoney(int userId,int amount);
}
