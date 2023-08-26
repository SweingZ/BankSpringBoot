package com.example.BankExample.service.impl;

import com.example.BankExample.DTO.PlanDTO;
import com.example.BankExample.model.Plan;
import com.example.BankExample.repository.PlanRepo;
import com.example.BankExample.service.PlanService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PlanServiceImpl implements PlanService {
    @Autowired
    private PlanRepo planRepo;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Override
    public void createPlan(PlanDTO planDTO) {
        Plan plan = this.modelMapper.map(planDTO,Plan.class);
        this.planRepo.save(plan);
    }

    @Override
    public void deletePlanById(int id) {
        this.planRepo.deleteById(id);
    }

    @Override
    public PlanDTO getPlanById(int id) {
        Optional<Plan> plan = planRepo.findById(id);
        if (plan.isEmpty()) throw new RuntimeException("Plan not Found !!!!");
        PlanDTO planDTO = this.modelMapper.map(plan, PlanDTO.class);
        return planDTO;
    }

    @Override
    public List<PlanDTO> getAllPlans() {
        List<Plan> plans = this.planRepo.findAll();
        List<PlanDTO> planDTOList = plans.stream().map((plan) -> {
            PlanDTO planDTO = modelMapper.map(plan, PlanDTO.class);
            return planDTO;
        }).toList();
        return planDTOList;
    }

    @Override
    public Plan updatePlan(Plan plan, int id) {
        return null;
    }
}
