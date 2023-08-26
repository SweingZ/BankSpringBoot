package com.example.BankExample.model;

import com.example.BankExample.enums.RoleEnum;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Data
@Table(name = "users")
public class User extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user",fetch = FetchType.LAZY)
    private List<Loan> loanList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id")
    private Agent agent;

    private final RoleEnum role = RoleEnum.ROLE_USER;
}
