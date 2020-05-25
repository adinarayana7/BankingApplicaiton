package com.example.BankingApplication1.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.BankingApplication1.model.SavingsAccount;
import com.example.BankingApplication1.service.SavingsService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class SavingsControllerTest {
	

	@InjectMocks
	SavingsController savingsController;

	@Mock
	SavingsService savingsService;

	@Test(expected = NullPointerException.class)
	public void testTransfer() {
		SavingsAccount savingsAccount = new SavingsAccount();
		savingsAccount.setAccountNumber((long) 5465);
		savingsAccount.setAccountBalance(50000);

		ResponseEntity<String> savings = savingsController.transfer(savingsAccount.getId(), 4, 9877, 9876565L);
		Assert.assertEquals("transaction was successfull", HttpStatus.OK);
	}

}
