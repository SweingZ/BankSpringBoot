package com.example.BankExample.model;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@Data
public class Person {
    private String username;

    private String fullName;

    private String password;

    private String address;

    private long phoneNumber;

    private String email;
}
