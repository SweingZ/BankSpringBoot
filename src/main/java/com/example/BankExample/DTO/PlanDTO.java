package com.example.BankExample.DTO;

import com.example.BankExample.model.Loan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PlanDTO {
    private int plan_id;
    private String details;
    private float interestRate;
    private List<LoanDTO> loanList;
    private List<AccountDTO> accountList;

}
