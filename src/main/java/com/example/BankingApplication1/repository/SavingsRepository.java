package com.example.BankingApplication1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BankingApplication1.model.SavingsAccount;

@Repository
public interface SavingsRepository extends JpaRepository<SavingsAccount,Long > {

}
