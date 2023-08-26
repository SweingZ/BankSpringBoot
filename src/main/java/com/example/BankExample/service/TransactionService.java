package com.example.BankExample.service;

import com.example.BankExample.DTO.TransactionDTO;
import com.example.BankExample.model.Transaction;
import java.util.List;

public interface TransactionService {
    void addTransaction(TransactionDTO transactionDTO);
    void deleteTransactionById(int id);
    TransactionDTO getTransactionById(int id);
    List<TransactionDTO> getAllTransactions();
    Transaction updateTransaction(Transaction transaction, int id);
    List<Transaction> getTransactionByUserId(int id);

}
