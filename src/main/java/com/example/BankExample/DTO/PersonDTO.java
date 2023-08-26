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
    private String username;

    private String fullName;

    private String password;

    private String address;

    private long phoneNumber;

    private String email;
}
