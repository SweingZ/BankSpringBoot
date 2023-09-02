package com.example.BankExample.service.impl;

import com.example.BankExample.DTO.AccountDTO;
import com.example.BankExample.DTO.TransactionDTO;
import com.example.BankExample.DTO.UserDTO;
import com.example.BankExample.model.*;
import com.example.BankExample.repository.AccountRepo;
import com.example.BankExample.repository.AgentRepo;
import com.example.BankExample.repository.TransactionRepo;
import com.example.BankExample.repository.UserRepo;
import com.example.BankExample.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AgentRepo agentRepo;

    @Autowired
    private TransactionRepo transactionRepo;

    @Override
    public void createUser(UserDTO userDTO) {
        User user = this.modelMapper.map(userDTO, User.class);
        LocalDate currentData = LocalDate.now();
        user.getAccount().setDateOpened(currentData);
        userRepo.save(user);
    }

    @Override
    public void deleteUserById(int id) {
        User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepo.deleteById(id);
    }

    @Override
    public UserDTO getUserById(int id) {
        User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);
        userDTO.setPassword(null);
        userDTO.setAgent(null);
        userDTO.setLoanList(null);
        userDTO.getAccount().setTransactionList(null);
        return userDTO;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = this.userRepo.findAll();
        List<UserDTO> userDTOList = users.stream().map((user) -> {
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            userDTO.setPassword(null);
            userDTO.setAgent(null);
            userDTO.setLoanList(null);
            userDTO.getAccount().setTransactionList(null);
            return userDTO;
        }).toList();
        return userDTOList;
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, int id) {
        User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        UserDTO savedUserDTO = this.modelMapper.map(user,UserDTO.class);
        if(Objects.nonNull(userDTO.getFirstName())){
            savedUserDTO.setFirstName(userDTO.getFirstName());
        }
        if(Objects.nonNull(userDTO.getLastName())){
            savedUserDTO.setLastName(userDTO.getLastName());
        }
        if(Objects.nonNull(userDTO.getEmail())){
            if(this.emailExists(userDTO.getEmail())) throw new RuntimeException("Email already taken");
            savedUserDTO.setEmail(userDTO.getEmail());
        }
        if(Objects.nonNull(userDTO.getDateOfBirth())){
            savedUserDTO.setDateOfBirth(userDTO.getDateOfBirth());
        }
        if(Objects.nonNull(userDTO.getAddress())){
            savedUserDTO.setAddress(userDTO.getAddress());
        }
        if(Objects.nonNull(userDTO.getPassword())){
            savedUserDTO.setPassword(userDTO.getPassword());
        }
        User savedUser = this.modelMapper.map(savedUserDTO,User.class);
        this.userRepo.save(savedUser);
        savedUserDTO.setPassword(null);
        savedUserDTO.setAgent(null);
        savedUserDTO.setLoanList(null);
        savedUserDTO.getAccount().setTransactionList(null);
        return savedUserDTO;
    }

    @Override
    public void addTransactionToUser(int userId, TransactionDTO transactionDTO) {
        int accountId = this.agentRepo.returnAccountId(userId);
        Account account = this.accountRepo.findById(accountId).orElseThrow(() -> new RuntimeException("Account Not Found"));
        Transaction transaction = this.modelMapper.map(transactionDTO,Transaction.class);
        LocalDate currentDate = LocalDate.now();
        transaction.setDateOfTransfer(currentDate);
        account.getTransactionList().add(transaction);
        transaction.setAccount(account);
        Account receiverAccount = this.accountRepo.findById(transaction.getReceiver_id()).orElseThrow(() -> new RuntimeException("Account Not Found"));
        int transferAmount = transactionDTO.getAmount();
        int newBalanceSender = account.getBalance() - transferAmount;
        if (newBalanceSender < 1000) throw new RuntimeException("Not enough Balance");
        account.setBalance(newBalanceSender);
        int newBalanceReceiver = receiverAccount.getBalance() + transferAmount;
        receiverAccount.setBalance(newBalanceReceiver);
        this.accountRepo.save(account);
//        this.transactionRepo.save(transaction);
        this.accountRepo.save(receiverAccount);
    }

    @Override
    public List<TransactionDTO> getUserTransactionList(int userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<Transaction> userTransactionList = user.getAccount().getTransactionList();
        List<TransactionDTO> transactionList = userTransactionList.stream().map((transaction) -> {
            TransactionDTO transactionDTO = modelMapper.map(transaction, TransactionDTO.class);
            transactionDTO.setAccount(null);
            return transactionDTO;
        }).toList();
        return transactionList;
    }

    private Boolean emailExists(String email) {
        Optional<User> savedUser = this.userRepo.findByEmail(email);
        return savedUser.isPresent();
    }
}
