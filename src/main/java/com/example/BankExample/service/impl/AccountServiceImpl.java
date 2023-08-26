package com.example.BankExample.service.impl;

import com.example.BankExample.DTO.AccountDTO;
import com.example.BankExample.model.Account;
import com.example.BankExample.repository.AccountRepo;
import com.example.BankExample.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public void createAccount(AccountDTO accountDTO) {
        Account account = this.modelMapper.map(accountDTO, Account.class);
        accountRepo.save(account);
    }

    @Override
    public void deleteAccountById(int id) {
        accountRepo.deleteById(id);
    }

    @Override
    public AccountDTO getAccountById(int id) {
        Optional<Account> account = accountRepo.findById(id);
        if (account.isEmpty()) throw new RuntimeException("Account not found");
        AccountDTO accountDTO = this.modelMapper.map(account, AccountDTO.class);
        return accountDTO;
    }

    @Override
    public List<AccountDTO> getAllAccounts() {
        List<Account> accounts = this.accountRepo.findAll();
        List<AccountDTO> accountDTOList = accounts.stream().map((account) -> {
            AccountDTO accountDTO = modelMapper.map(account, AccountDTO.class);
            return accountDTO;
        }).toList();
        return accountDTOList;
    }

    @Override
    public AccountDTO updateAccount(AccountDTO accountDTO, int id) {
         return null;
    }
}
