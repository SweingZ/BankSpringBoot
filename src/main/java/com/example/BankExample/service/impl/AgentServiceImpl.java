package com.example.BankExample.service.impl;

import com.example.BankExample.DTO.AgentDTO;
import com.example.BankExample.DTO.UserDTO;
import com.example.BankExample.model.Account;
import com.example.BankExample.model.Agent;
import com.example.BankExample.model.User;
import com.example.BankExample.repository.AccountRepo;
import com.example.BankExample.repository.AgentRepo;
import com.example.BankExample.repository.UserRepo;
import com.example.BankExample.service.AgentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
public class AgentServiceImpl implements AgentService {
    @Autowired
    private AgentRepo agentRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void createAgent(AgentDTO agentDTO) {
        Agent agent = this.modelMapper.map(agentDTO,Agent.class);
        if (this.emailExists(agent.getEmail())){
            throw new RuntimeException("Email already taken");
        }
        if (this.phoneExists(agent.getPhoneNumber())){
            throw new RuntimeException("Phone number already taken");
        }
        agent.setPassword(passwordEncoder.encode(agent.getPassword()));
        this.agentRepo.save(agent);
    }

    private Boolean emailExists(String email) {
        Optional<Agent> savedAgent = this.agentRepo.findByEmail(email);
        return savedAgent.isPresent();
    }

    private Boolean phoneExists(long phone){
        Optional<Agent> savedAgent = this.agentRepo.findByPhoneNumber(phone);
        return savedAgent.isPresent();
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
        agentDTO.setUserList(null);
        return agentDTO;
    }

    @Override
    public List<AgentDTO> getAllAgents() {
        List<Agent> agents = this.agentRepo.findAll();
        List<AgentDTO> agentDTOList = agents.stream().map((agent) -> {
            AgentDTO agentDTO = modelMapper.map(agent, AgentDTO.class);
            agentDTO.setPassword(null);
            agentDTO.setUserList(null);
            return agentDTO;
        }).toList();
        return agentDTOList;
    }

    @Override
    public Agent updateAgent(Agent agent, int id) {
        return null;
    }

    @Override
    public void addUserToAgent(int agentId, int userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
        Agent agent = this.agentRepo.findById(agentId).orElseThrow(()-> new RuntimeException("Agent not found"));
        agent.getUserList().add(user);
        user.setAgent(agent);
        userRepo.save(user);
        agentRepo.save(agent);
    }

    @Override
    public List<UserDTO> getUserList(int agentId) {
        Agent agent = agentRepo.findById(agentId).orElseThrow(() -> new RuntimeException("Agent Not Found"));
        List<User> userList = agent.getUserList();
        List<UserDTO> userDTOList = userList.stream().map(user ->{
            UserDTO userDTO = modelMapper.map(user,UserDTO.class);
            userDTO.setPassword(null);
            userDTO.setAgent(null);
            userDTO.setLoanList(null);
            userDTO.getAccount().setTransactionList(null);
            return userDTO;
        }).toList();
        return userDTOList;
    }

    @Override
    public void depositMoney(int userId, int amount) {
        int accountId = this.agentRepo.returnAccountId(userId);
        Account account = this.accountRepo.findById(accountId).orElseThrow(() -> new RuntimeException("Account Not Found"));
        int newBalance = account.getBalance() + amount;
        account.setBalance(newBalance);
        this.accountRepo.save(account);
    }

    @Override
    public void withdrawMoney(int userId, int amount) {
        int accountId = this.agentRepo.returnAccountId(userId);
        Account account = this.accountRepo.findById(accountId).orElseThrow(() -> new RuntimeException("Account Not Found"));
        int newBalance = account.getBalance() - amount;
        if (newBalance < 1000) throw new RuntimeException("Not enough Balance");
        account.setBalance(newBalance);
        this.accountRepo.save(account);
    }
}
