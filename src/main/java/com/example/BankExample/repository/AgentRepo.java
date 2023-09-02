package com.example.BankExample.repository;

import com.example.BankExample.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgentRepo extends JpaRepository<Agent,Integer> {
    Optional<Agent> findByEmail(String email);

    Optional<Agent> findByPhoneNumber(long phone);
    @Query(value = "SELECT account_id FROM users WHERE user_id = :id",nativeQuery = true)
    public int returnAccountId(@Param("id")int user_id);

}
