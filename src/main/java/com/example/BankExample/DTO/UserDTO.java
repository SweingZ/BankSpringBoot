package com.example.BankExample.DTO;

import com.example.BankExample.enums.RoleEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO extends PersonDTO {
    private int user_id;
    private LocalDate dateOfBirth;
    private AccountDTO account;
    private List<LoanDTO> loanList;
    private AgentDTO agent;
    private final RoleEnum role = RoleEnum.ROLE_USER;

    public UserDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }


}
