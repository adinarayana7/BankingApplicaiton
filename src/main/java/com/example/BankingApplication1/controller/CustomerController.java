package com.example.BankingApplication1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.BankingApplication1.exceptions.CustomerNotLoggedIn;
import com.example.BankingApplication1.exceptions.InavalidCredentials;
import com.example.BankingApplication1.model.Customer;
import com.example.BankingApplication1.service.CustomerService;
import com.example.BankingApplication1.utility.PasswordGeneration;

@RestController
public class CustomerController {
	@Autowired
	CustomerService customerService;
	PasswordGeneration password = password = new PasswordGeneration();

	@PostMapping("/registration")
	public ResponseEntity<String> customerRegistration(@RequestBody Customer customer) {
		Customer customer1 = new Customer();
		if (customer.getPassword() == null) {
			String pass = password.generatePassword(10);
			customer.setPassword(pass);

			customer1 = customerService.customerRegistration(customer);
		}

		return new ResponseEntity<String>("customerEmailId" + " " + customer1.getEmail() + "   "
				+ "customer password is" + "  " + customer1.getPassword(), HttpStatus.OK);
	}

	@GetMapping("/login")
	public ResponseEntity<String> Login(@RequestParam String email, @RequestParam String password) {
		Customer customer = customerService.customerLogin(email, password);
		if (password.equals(customer.getPassword())) {
			customer.setEnabled(true);
			customerService.UpdateCustomer(customer);
			return new ResponseEntity<String>("logged in successfully", HttpStatus.OK);
		} else {
			throw new InavalidCredentials();
		}
	}

	public ResponseEntity<String> isValidCustomer(@RequestParam long id) {
		Customer customer = customerService.getCustomerById(id);
		if (customer.isEnabled()) {
			return new ResponseEntity<String>("user logged in successfully", HttpStatus.OK);
		} else {
			throw new CustomerNotLoggedIn();
		}
	}

}
