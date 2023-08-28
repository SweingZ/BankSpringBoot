package com.example.BankExample.controller;

import com.example.BankExample.DTO.PlanDTO;
import com.example.BankExample.model.Plan;
import com.example.BankExample.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlanController {
    @Autowired
    private PlanService planService;

    @PostMapping("plan")
    public ResponseEntity<String> addPlan(@RequestBody PlanDTO planDTO){
        this.planService.createPlan(planDTO);
        return ResponseEntity.status(200).body("Plan added");
    }

    @DeleteMapping("plan/{id}")
    public ResponseEntity<String> deletePlanById(@PathVariable("id") int id){
        this.planService.deletePlanById(id);
        return ResponseEntity.status(200).body("Plan Deleted");
    }

    @GetMapping("plan")
    public List<PlanDTO> getAllPlans(){
        return this.planService.getAllPlans();
    }

    @GetMapping("plan/{id}")
    public ResponseEntity<PlanDTO> getPlanById(@PathVariable("id") int id){
        PlanDTO planDTO = planService.getPlanById(id);
        return ResponseEntity.status(200).body(planDTO);
    }
}
