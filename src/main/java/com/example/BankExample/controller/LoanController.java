package com.example.BankExample.controller;

import com.example.BankExample.DTO.LoanDTO;
import com.example.BankExample.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoanController {
    @Autowired
    private LoanService loanService;

    @PostMapping("loan")
    public ResponseEntity<String> addLoan(@RequestBody LoanDTO loanDTO){
        this.loanService.addLoan(loanDTO);
        return ResponseEntity.status(200).body("Loan added");
    }

    @DeleteMapping("loan/{id}")
    public ResponseEntity<String> deleteLoanById(@PathVariable("id") int id){
        this.loanService.deleteLoanById(id);
        return ResponseEntity.status(200).body("Loan Deleted");
    }

    @GetMapping("loan")
    public List<LoanDTO> getAllLoans(){
        return this.loanService.getAllLoans();
    }

    @GetMapping("loan/{id}")
    public ResponseEntity<LoanDTO> getLoanById(@PathVariable("id") int id){
        LoanDTO loanDTO = loanService.getLoanById(id);
        return ResponseEntity.status(200).body(loanDTO);
    }

}
