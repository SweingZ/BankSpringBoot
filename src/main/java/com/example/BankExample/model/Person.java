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
    protected String firstName;

    protected String lastName;

    protected String email;

    protected String password;

    protected String address;

    protected long phoneNumber;

}
