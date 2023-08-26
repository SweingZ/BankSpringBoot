package com.example.BankExample.model;

import com.example.BankExample.enums.RoleEnum;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Data
public class Admin extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int admin_id;
    private final RoleEnum role = RoleEnum.ROLE_ADMIN;

}
