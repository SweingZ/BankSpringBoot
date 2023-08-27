package com.example.BankExample.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonDTO {
    protected String firstName;

    protected String lastName;

    @Email(message = "Not valid Email")
    protected String email;

    protected String password;

    protected String address;

    protected long phoneNumber;
}
