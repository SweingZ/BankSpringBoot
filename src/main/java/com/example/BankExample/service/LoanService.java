package com.example.BankExample.service;

import com.example.BankExample.DTO.LoanDTO;
import com.example.BankExample.model.Loan;

import java.util.List;

public interface LoanService {
    void addLoan(LoanDTO loanDTO);
    void deleteLoanById(int id);
    LoanDTO getLoanById(int id);
    List<LoanDTO> getAllLoans();
    Loan updateLoan(Loan loan,int id);
}
