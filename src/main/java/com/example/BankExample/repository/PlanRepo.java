package com.example.BankExample.repository;

import com.example.BankExample.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepo extends JpaRepository<Plan,Integer> {
}
