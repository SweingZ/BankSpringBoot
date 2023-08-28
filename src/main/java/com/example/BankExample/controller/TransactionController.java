package com.example.BankExample.controller;

import com.example.BankExample.DTO.TransactionDTO;
import com.example.BankExample.model.Transaction;
import com.example.BankExample.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("transaction")
    public ResponseEntity<String> addTransaction(@RequestBody TransactionDTO transactionDTO){
        transactionService.addTransaction(transactionDTO);
        return ResponseEntity.status(201).body("Transaction added");
    }

    @DeleteMapping("transaction/{id}")
    public ResponseEntity<String> deleteTransactionById(@PathVariable("id") int id){
        transactionService.deleteTransactionById(id);
        return ResponseEntity.status(200).body("Transaction Deleted");
    }

    @GetMapping("transaction")
    public List<TransactionDTO> getAllTransactions(){
        return transactionService.getAllTransactions();
    }

    @GetMapping("transaction/{id}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable("id") int id){
        TransactionDTO transactionDTO = transactionService.getTransactionById(id);
        return ResponseEntity.status(200).body(transactionDTO);
    }


}
