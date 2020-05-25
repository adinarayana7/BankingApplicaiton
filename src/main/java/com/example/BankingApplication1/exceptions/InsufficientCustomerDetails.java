package com.example.BankingApplication1.exceptions;

public class InsufficientCustomerDetails extends RuntimeException {
	public InsufficientCustomerDetails() {
		super("Insufficient Customer Details");
	}

}
