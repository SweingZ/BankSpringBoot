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
        User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);
        userDTO.setPassword(null);
        userDTO.setAgent(null);
        return userDTO;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = this.userRepo.findAll();
        List<UserDTO> userDTOList = users.stream().map((user) -> {
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            userDTO.setPassword(null);
            userDTO.setAgent(null);
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
        return savedUserDTO;
    }
}
