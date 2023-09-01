package com.example.BankExample.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDTO {
    private int Transaction_id;
    private int amount;
    private LocalDate dateOfTransfer;
    private String remarks;
    private int receiver_id;
    private AccountDTO account;

}
