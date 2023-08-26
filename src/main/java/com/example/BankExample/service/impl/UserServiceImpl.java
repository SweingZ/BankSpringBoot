package com.example.BankExample.service.impl;

import com.example.BankExample.DTO.UserDTO;
import com.example.BankExample.model.Account;
import com.example.BankExample.model.User;
import com.example.BankExample.repository.AccountRepo;
import com.example.BankExample.repository.UserRepo;
import com.example.BankExample.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void createUser(UserDTO userDTO) {
        User user = this.modelMapper.map(userDTO, User.class);
        LocalDate currentData = LocalDate.now();
        user.getAccount().setDateOpened(currentData);
        userRepo.save(user);
    }

    @Override
    public void deleteUserById(int id) {
        userRepo.deleteById(id);
    }

    @Override
    public UserDTO getUserById(int id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isEmpty()) throw new RuntimeException("User not found");
        UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);
        userDTO.setPassword(null);
        return userDTO;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = this.userRepo.findAll();
        List<UserDTO> userDTOList = users.stream().map((user) -> {
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            userDTO.setPassword(null);
            return userDTO;
        }).toList();
        return userDTOList;
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, int id) {
        return null;
    }
}
