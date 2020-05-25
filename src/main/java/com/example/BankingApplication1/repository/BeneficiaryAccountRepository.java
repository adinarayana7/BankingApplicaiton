package com.example.BankingApplication1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BankingApplication1.model.BeneficiaryAccount;

public interface BeneficiaryAccountRepository extends JpaRepository<BeneficiaryAccount,Long>{
	 public BeneficiaryAccount findByAccountNumber(long accountNumber);
	

}
