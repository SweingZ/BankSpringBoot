package com.example.BankExample.controller;

import com.example.BankExample.DTO.AccountDTO;
import com.example.BankExample.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("account")
    public ResponseEntity<String> createAccount(@RequestBody AccountDTO accountDTO){
        this.accountService.createAccount(accountDTO);
        return ResponseEntity.status(201).body("Account Created");
    }

    @DeleteMapping("account/{id}")
    public ResponseEntity<String> deleteAccountById(@PathVariable int id){
        accountService.deleteAccountById(id);
        return ResponseEntity.status(201).body("Account Deleted");
    }

    @GetMapping("account")
    public List<AccountDTO> getAllAccounts(){
        return accountService.getAllAccounts();
    }

    @GetMapping("account/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable int id){
        AccountDTO accountDTO = accountService.getAccountById(id);
        return ResponseEntity.status(200).body(accountDTO);
    }

}
