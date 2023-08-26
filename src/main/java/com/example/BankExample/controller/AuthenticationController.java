package com.example.BankExample.controller;

import com.example.BankExample.DTO.LoginRequestDTO;
import com.example.BankExample.DTO.LoginResponseDTO;
import com.example.BankExample.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok().body(this.authenticationService.login(loginRequestDTO));
    }
}
