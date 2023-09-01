package com.example.BankExample.DTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDTO {
    private int account_id;
    private int balance;
    private LocalDate dateOpened;
    private final float interestRate = 6.9f;

    @JsonBackReference
    private UserDTO user;
    private List<TransactionDTO> transactionList;
    private PlanDTO plan;


}
