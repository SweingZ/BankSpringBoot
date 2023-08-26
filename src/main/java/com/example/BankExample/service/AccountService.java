package com.example.BankExample.service;

import com.example.BankExample.DTO.AccountDTO;
import com.example.BankExample.model.Account;

import java.util.List;

public interface AccountService {
    void createAccount(AccountDTO accountDTO);
    void deleteAccountById(int id);
    AccountDTO getAccountById(int id);
    List<AccountDTO> getAllAccounts();
    AccountDTO updateAccount(AccountDTO accountDTO, int id);
}
