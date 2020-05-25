package com.example.BankingApplication1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BankingApplication1.exceptions.BeneficiaryAccountNotFound;
import com.example.BankingApplication1.model.BeneficiaryAccount;
import com.example.BankingApplication1.model.SavingsAccount;
import com.example.BankingApplication1.repository.BeneficiaryAccountRepository;
@Service
public class BeneficiaryAccountService {
	@Autowired
	BeneficiaryAccountRepository beneficiaryAccountRepository;
	
	public BeneficiaryAccount getBeneficiaryAccount(Long accountNumber) {
		try {
			 return beneficiaryAccountRepository.findById(accountNumber).get();
			
		}
		catch(Exception e) {
			throw new BeneficiaryAccountNotFound();
		}
		
			}
	
	
	
	public void updateBeneficiaryAccountRepository(BeneficiaryAccount beneficiaryAccount) {
		
		beneficiaryAccountRepository.save(beneficiaryAccount);
		
	}

}
