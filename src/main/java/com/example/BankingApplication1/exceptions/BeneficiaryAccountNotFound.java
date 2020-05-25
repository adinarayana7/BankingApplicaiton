package com.example.BankingApplication1.exceptions;

public class BeneficiaryAccountNotFound extends RuntimeException {
	public BeneficiaryAccountNotFound(){
		super("Beneficiary AccountNumber is incorrect");
		
	}

}
