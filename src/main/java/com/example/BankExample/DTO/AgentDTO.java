package com.example.BankExample.DTO;

import com.example.BankExample.enums.RoleEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AgentDTO extends PersonDTO{
    private int agent_id;
    private List<UserDTO> userList;
    private final RoleEnum role = RoleEnum.ROLE_AGENT;

    public AgentDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
