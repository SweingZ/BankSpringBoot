package com.example.BankExample.service.impl;

import com.example.BankExample.DTO.TransactionDTO;
import com.example.BankExample.model.Transaction;
import com.example.BankExample.repository.TransactionRepo;
import com.example.BankExample.service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepo transactionRepo;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Override
    public void addTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = this.modelMapper.map(transactionDTO, Transaction.class);
        LocalDate currentDate = LocalDate.now();
        transaction.setDateOfTransfer(currentDate);
        transactionRepo.save(transaction);
    }

    @Override
    public void deleteTransactionById(int id) {
        transactionRepo.deleteById(id);
    }

    @Override
    public TransactionDTO getTransactionById(int id) {
        Optional<Transaction> transaction = transactionRepo.findById(id);
        if (transaction.isEmpty()) throw new RuntimeException("No Transaction found !!!");
        TransactionDTO transactionDTO = this.modelMapper.map(transaction, TransactionDTO.class);
        return transactionDTO;
    }

    @Override
    public List<TransactionDTO> getAllTransactions() {
        List<Transaction> transactions = this.transactionRepo.findAll();
        List<TransactionDTO> transactionDTOList = transactions.stream().map((transaction) -> {
            TransactionDTO transactionDTO = modelMapper.map(transaction, TransactionDTO.class);
            return transactionDTO;
        }).toList();
        return transactionDTOList;
    }

    @Override
    public Transaction updateTransaction(Transaction transaction, int id) {
        return null;
    }

    @Override
    public List<Transaction> getTransactionByUserId(int id) {
        return null;
    }
}
