package com.example.BankExample.service;

import com.example.BankExample.DTO.LoginRequestDTO;
import com.example.BankExample.DTO.LoginResponseDTO;
import com.example.BankExample.DTO.UserDTO;
import com.example.BankExample.enums.RoleEnum;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface AuthenticationService {
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);

    UserDTO registerAsUser(UserDTO userDTO);

    void createAdmin();

    UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(String username, RoleEnum role);
}
