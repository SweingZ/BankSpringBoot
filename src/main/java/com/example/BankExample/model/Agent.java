package com.example.BankExample.model;

import com.example.BankExample.enums.RoleEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Agent extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int agent_id;

    @OneToMany(mappedBy = "agent")
    @JsonBackReference
    private List<User> userList = new ArrayList<>();

    private final RoleEnum role = RoleEnum.ROLE_AGENT;

}
