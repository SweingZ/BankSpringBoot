package com.example.BankExample.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int plan_id;
    private String details;
    private float interestRate;

    @JsonBackReference
    @OneToMany(mappedBy = "plan",cascade = CascadeType.ALL)
    private List<Loan> loanList = new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "plan")
    private List<Account> accountList = new ArrayList<>();
}
