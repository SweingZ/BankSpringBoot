package com.example.BankExample.service;

import com.example.BankExample.DTO.TransactionDTO;
import com.example.BankExample.DTO.UserDTO;
import com.example.BankExample.model.User;

import java.util.List;

public interface UserService {
    void createUser(UserDTO userDTO);
    void deleteUserById(int id);
    UserDTO getUserById(int id);
    List<UserDTO> getAllUsers();
    UserDTO updateUser(UserDTO userDTO, int id);
    void addTransactionToUser(int userId, TransactionDTO transactionDTO);
}
