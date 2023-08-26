package com.example.BankExample.controller;

import com.example.BankExample.DTO.UserDTO;
import com.example.BankExample.model.User;
import com.example.BankExample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("register")
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO){
        this.userService.createUser(userDTO);
        return ResponseEntity.status(201).body("User Created");
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable int id){
        userService.deleteUserById(id);
        return ResponseEntity.status(201).body("User Deleted");
    }

    @GetMapping("user")
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("user/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int id){
        UserDTO userDTO = userService.getUserById(id);
        return ResponseEntity.status(200).body(userDTO);
    }

}
