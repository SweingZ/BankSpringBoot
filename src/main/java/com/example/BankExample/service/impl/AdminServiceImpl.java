package com.example.BankExample.service.impl;

import com.example.BankExample.DTO.AdminDTO;
import com.example.BankExample.model.Admin;
import com.example.BankExample.repository.AdminRepo;
import com.example.BankExample.service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class
AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public void createAdmin(AdminDTO adminDTO) {
        Admin admin = this.modelMapper.map(adminDTO,Admin.class);
        this.adminRepo.save(admin);
    }
}
