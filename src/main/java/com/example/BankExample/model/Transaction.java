package com.example.BankExample.model;

import javax.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Transaction_id;
    private int amount;
    private LocalDate dateOfTransfer;
    private String remarks;
    private int receiver_id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
