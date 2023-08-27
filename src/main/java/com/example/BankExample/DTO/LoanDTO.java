package com.example.BankExample.DTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LoanDTO {
    private int loan_id;
    private int principal_amount;
    private LocalDate dateBorrowed;
    private UserDTO user;
    private PlanDTO plan;


}
