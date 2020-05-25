package com.example.BankingApplication1.exceptions;

public class InsufficientBalance extends RuntimeException {
	public InsufficientBalance() {
		super("entered amount is greater than the available balance");
	}

}
