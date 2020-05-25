package com.example.BankingApplication1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.BankingApplication1.model.Customer;
import com.example.BankingApplication1.model.SavingsAccount;
import com.example.BankingApplication1.service.SavingsService;

@RestController
public class SavingsController {
	@Autowired
	SavingsService savingsService;

	@Autowired
	CustomerController customerController;

	@GetMapping("/transaction")
	public ResponseEntity<String> transfer(@RequestParam long id, @RequestParam long accountNumber,
			@RequestParam long amount, long customerId) {
		customerController.isValidCustomer(customerId);
		savingsService.getSavingsAccount(id, accountNumber, amount);

		return new ResponseEntity<String>("transaction was successfull", HttpStatus.OK);

	}
	
}
