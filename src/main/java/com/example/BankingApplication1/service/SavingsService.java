package com.example.BankingApplication1.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BankingApplication1.exceptions.BeneficiaryAccountNotFound;
import com.example.BankingApplication1.exceptions.InsufficientBalance;
import com.example.BankingApplication1.exceptions.SavingsAccountIdNotFoundException;
import com.example.BankingApplication1.model.BeneficiaryAccount;
import com.example.BankingApplication1.model.SavingsAccount;
import com.example.BankingApplication1.repository.SavingsRepository;

@Service
public class SavingsService {
	@Autowired
	SavingsRepository savingsRepository;
	@Autowired
	BeneficiaryAccountService beneficiaryAccountService;

	public void getSavingsAccount(long id, long accountNumber, long amount) {
		SavingsAccount savingsAccount;
		
try {
		savingsAccount = savingsRepository.findById(id).get();
}
catch(Exception e) {
	throw new SavingsAccountIdNotFoundException();
}
		
		if(savingsAccount.getAccountBalance()>amount) {
			savingsAccount.setAccountBalance(savingsAccount.getAccountBalance() - amount);
			BeneficiaryAccount beneficiaryAccount;
			try {
				
			 beneficiaryAccount = beneficiaryAccountService.getBeneficiaryAccount(accountNumber);
			}
			catch (Exception e){
				throw new BeneficiaryAccountNotFound();
				
			}
			
			beneficiaryAccount.setAccountBalance(beneficiaryAccount.getAccountBalance() + amount);
			beneficiaryAccountService.updateBeneficiaryAccountRepository(beneficiaryAccount);
			savingsRepository.save(savingsAccount);
		}
		else
		{
			throw new InsufficientBalance();
		}
		
	}
}
