package com.example.BankExample.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.MappedSuperclass;
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

    protected String email;

    protected String password;

    protected String address;

    protected long phoneNumber;
}
