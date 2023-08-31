package com.example.BankExample.service.impl;

import com.example.BankExample.DTO.AccountDTO;
import com.example.BankExample.model.Account;
import com.example.BankExample.repository.AccountRepo;
import com.example.BankExample.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduledTask {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Scheduled(cron = "0 0 0 1 * *")
    public void runTask(){
        List<AccountDTO> accountDTOList = this.accountService.getAllAccounts();
        accountDTOList.stream().map(accountDTO ->{
            Account account = this.modelMapper.map(accountDTO, Account.class);
            int interestAmount = (int) (account.getInterestRate()/100 * account.getBalance());
            int newBalance = (account.getBalance() + interestAmount);
            account.setBalance(newBalance);
            this.accountRepo.save(account);
            return account;
        });
    }
}
