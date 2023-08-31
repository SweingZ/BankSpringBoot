package com.example.BankExample.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int account_id;
    private int balance;
    private LocalDate dateOpened;
    private final float interestRate = 6.9f;

    @JsonBackReference
    @OneToOne(mappedBy = "account")
    private User user;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "account")
    @JsonBackReference
    private List<Transaction> transactionList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;
}
