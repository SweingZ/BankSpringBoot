package com.example.BankExample.controller;

import com.example.BankExample.DTO.AgentDTO;
import com.example.BankExample.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AgentController {
    @Autowired
    private AgentService agentService;

    @PostMapping("agent")
    public ResponseEntity<String> addAgent(@RequestBody AgentDTO agentDTO){
        this.agentService.createAgent(agentDTO);
        return ResponseEntity.status(200).body("Agent Created");
    }

    @DeleteMapping("agent/{id}")
    public ResponseEntity<String> deleteAgentById(@PathVariable("id") int id){
        this.agentService.deleteAgentById(id);
        return ResponseEntity.status(200).body("Agent Deleted");
    }

    @GetMapping("agent")
    public List<AgentDTO> getAllAgents(){
        return this.agentService.getAllAgents();
    }

    @GetMapping("agent/{id}")
    public ResponseEntity<AgentDTO> getAccountById(@PathVariable("id") int id){
        AgentDTO agentDTO = agentService.getAgentById(id);
        return ResponseEntity.status(200).body(agentDTO);
    }

}
