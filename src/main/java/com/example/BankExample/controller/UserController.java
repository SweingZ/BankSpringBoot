package com.example.BankExample.controller;

import com.example.BankExample.DTO.TransactionDTO;
import com.example.BankExample.DTO.UserDTO;
import com.example.BankExample.model.User;
import com.example.BankExample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("user")
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO){
        this.userService.createUser(userDTO);
        return ResponseEntity.status(201).body("User Created");
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") int id){
        userService.deleteUserById(id);
        return ResponseEntity.status(201).body("User Deleted");
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_AGENT')")
    @GetMapping("user")
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("user/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") int id){
        UserDTO userDTO = userService.getUserById(id);
        return ResponseEntity.status(200).body(userDTO);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PutMapping("user/{id}")
    public ResponseEntity<UserDTO> updateUserById(@PathVariable("id") int id,@RequestBody @Valid UserDTO userDTO){
        UserDTO savedUserDTO = this.userService.updateUser(userDTO,id);
        return ResponseEntity.status(200).body(savedUserDTO);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("user/{userId}/transaction")
    public ResponseEntity<String> addTransactionToUserAccount(@PathVariable("userId")int userId, @RequestBody TransactionDTO transactionDTO){
        userService.addTransactionToUser(userId,transactionDTO);
        return ResponseEntity.status(200).body("Transaction successful");
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("user/{userId}/transactionList")
    public List<TransactionDTO> getTransactionList(@PathVariable("userId") int userId){
        return this.userService.getUserTransactionList(userId);
    }

}
