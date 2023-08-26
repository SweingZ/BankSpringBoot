package com.example.BankExample.service;

import com.example.BankExample.DTO.PlanDTO;
import com.example.BankExample.model.Plan;

import java.util.List;

public interface PlanService {
    void createPlan(PlanDTO planDTO);
    void deletePlanById(int id);
    PlanDTO getPlanById(int id);
    List<PlanDTO> getAllPlans();
    Plan updatePlan(Plan plan, int id);

}
