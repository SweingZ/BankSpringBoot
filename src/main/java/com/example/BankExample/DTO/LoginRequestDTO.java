package com.example.BankExample.DTO;

import com.example.BankExample.enums.RoleEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginRequestDTO {
    private String username;
    private String password;
    private RoleEnum role;
}
