package com.example.BankExample.repository;

import com.example.BankExample.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepo extends JpaRepository<Loan,Integer> {
}
