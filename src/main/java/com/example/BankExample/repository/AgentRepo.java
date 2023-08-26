package com.example.BankExample.repository;

import com.example.BankExample.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgentRepo extends JpaRepository<Agent,Integer> {
    Optional<Agent> findByUsername(String username);

}
