package com.example.BankExample.controller;

import com.example.BankExample.DTO.AgentDTO;
import com.example.BankExample.DTO.UserDTO;
import com.example.BankExample.model.User;
import com.example.BankExample.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AgentController {
    @Autowired
    private AgentService agentService;

    @PostMapping("agent")
    public ResponseEntity<String> addAgent(@RequestBody @Valid AgentDTO agentDTO){
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
    public ResponseEntity<AgentDTO> getAgentById(@PathVariable("id") int id){
        AgentDTO agentDTO = agentService.getAgentById(id);
        return ResponseEntity.status(200).body(agentDTO);
    }

    @PostMapping("agent/user/{agentId}/{userId}")
    public ResponseEntity<String> addUserToAgent(@PathVariable("agentId") int agentId,@PathVariable("userId") int userId){
        agentService.addUserToAgent(agentId,userId);
        return ResponseEntity.status(200).body("User successfully added to Agent");
    }

    @GetMapping("agent/{agentId}/user")
    public List<UserDTO> getUserList(@PathVariable("agentId") int agentId){
        return this.agentService.getUserList(agentId);
    }
}
