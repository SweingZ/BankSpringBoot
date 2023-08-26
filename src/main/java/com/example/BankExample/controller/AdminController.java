package com.example.BankExample.controller;

import com.example.BankExample.DTO.AdminDTO;
import com.example.BankExample.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("admin")
    public ResponseEntity<String> createAdmin(@RequestBody AdminDTO adminDTO){
        this.adminService.createAdmin(adminDTO);
        return ResponseEntity.status(200).body("Admin Created");
    }

}
