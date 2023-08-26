package com.example.BankExample.DTO;

import com.example.BankExample.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AdminDTO extends PersonDTO{
    private int admin_id;
    private final RoleEnum role = RoleEnum.ROLE_ADMIN;
}
