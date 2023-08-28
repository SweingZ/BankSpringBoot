package com.example.BankExample.service.impl;

import com.example.BankExample.DTO.LoanDTO;
import com.example.BankExample.model.Loan;
import com.example.BankExample.repository.LoanRepo;
import com.example.BankExample.service.LoanService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {
    @Autowired
    private LoanRepo loanRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public void addLoan(LoanDTO loanDTO) {
        Loan loan = this.modelMapper.map(loanDTO,Loan.class);
        LocalDate currentDate = LocalDate.now();
        loan.setDateBorrowed(currentDate);
        this.loanRepo.save(loan);
    }

    @Override
    public void deleteLoanById(int id) {
        this.loanRepo.deleteById(id);
    }

    @Override
    public LoanDTO getLoanById(int id) {
        Loan loan = loanRepo.findById(id).orElseThrow(() -> new RuntimeException("Loan Not Found"));
        LoanDTO loanDTO = this.modelMapper.map(loan, LoanDTO.class);
        return loanDTO;
    }

    @Override
    public List<LoanDTO> getAllLoans() {
        List<Loan> loans = this.loanRepo.findAll();
        List<LoanDTO> loanDTOList = loans.stream().map((loan) -> {
            LoanDTO loanDTO = modelMapper.map(loan, LoanDTO.class);
            return loanDTO;
        }).toList();
        return loanDTOList;
    }

    @Override
    public Loan updateLoan(Loan loan, int id) {
        return null;
    }
}
