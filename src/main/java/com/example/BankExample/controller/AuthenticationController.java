package com.example.BankExample.controller;

import com.example.BankExample.DTO.LoginRequestDTO;
import com.example.BankExample.DTO.LoginResponseDTO;
import com.example.BankExample.DTO.UserDTO;
import com.example.BankExample.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("register")
    public ResponseEntity<UserDTO> registerAsUser(@RequestBody @Valid UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.registerAsUser(userDTO));
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok().body(this.authenticationService.login(loginRequestDTO));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("jwt/admin")
    public ResponseEntity<String> onlyAdmin() {
        return ResponseEntity.ok().body("Only Admin");
    }

    @PreAuthorize(("hasAuthority('ROLE_AGENT')"))
    @GetMapping("jwt/agent")
    public ResponseEntity<String> onlyAgent(){
        return ResponseEntity.ok().body("Only Agent");
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("jwt/user")
    public ResponseEntity<String> userAndAdmin() {
        return ResponseEntity.ok().body("User And Admin");
    }
}
