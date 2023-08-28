package com.example.BankExample.service.impl;

import com.example.BankExample.DTO.AgentDTO;
import com.example.BankExample.model.Agent;
import com.example.BankExample.repository.AgentRepo;
import com.example.BankExample.service.AgentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentServiceImpl implements AgentService {
    @Autowired
    private AgentRepo agentRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public void createAgent(AgentDTO agentDTO) {
        Agent agent = this.modelMapper.map(agentDTO,Agent.class);
        this.agentRepo.save(agent);
    }

    @Override
    public void deleteAgentById(int id) {
        this.agentRepo.deleteById(id);
    }

    @Override
    public AgentDTO getAgentById(int id) {
        Agent agent = agentRepo.findById(id).orElseThrow(() -> new RuntimeException("Agent Not Found"));
        AgentDTO agentDTO = this.modelMapper.map(agent, AgentDTO.class);
        agentDTO.setPassword(null);
        return agentDTO;
    }

    @Override
    public List<AgentDTO> getAllAgents() {
        List<Agent> agents = this.agentRepo.findAll();
        List<AgentDTO> agentDTOList = agents.stream().map((agent) -> {
            AgentDTO agentDTO = modelMapper.map(agent, AgentDTO.class);
            agentDTO.setPassword(null);
            return agentDTO;
        }).toList();
        return agentDTOList;
    }

    @Override
    public Agent updateAgent(Agent agent, int id) {
        return null;
    }
}
