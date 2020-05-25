package com.example.BankingApplication1.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.example.BankingApplication1.model.BeneficiaryAccount;
import com.example.BankingApplication1.model.Customer;
import com.example.BankingApplication1.model.SavingsAccount;
import com.example.BankingApplication1.repository.CustomerRepository;
import com.example.BankingApplication1.service.CustomerService;

import junit.framework.Assert;

public class CustomerServiceTest {
	
	@InjectMocks
	CustomerService customerService;

	@Mock
	CustomerRepository customerRepository;

	@Test
	public void TestSaveCustomerForPostive() {
		Customer customer = new Customer();
		customer.setCustomerId(1L);
		customer.setFirstName("kavya");
		customer.setLastName("kinthali");
		customer.setEmail("kavya@gmail.com");
		customer.setPassword("kavya");
		customer.setPanNo("323547345");
		customer.setPhone("9876543212");

		SavingsAccount savingsAccount = new SavingsAccount();
		savingsAccount.setAccountNumber((long) 23453245);
		savingsAccount.setAccountBalance(64323L);
		customer.setSavingsAccount(savingsAccount);


		customerService.customerRegistration(customer);
		Mockito.verify(customerRepository, Mockito.times(1)).save(customer);

	}
	@Test
	public void TestSaveCustomerForNegative() {

		Customer customer1 = new Customer();
		customer1.setCustomerId(2L);
		customer1.setFirstName("kavya");
		customer1.setLastName("kinthali");
		customer1.setEmail("kavya@gmail.com");
		customer1.setPassword("kavya");
		customer1.setPanNo("323547345");
		
		Customer customer = new Customer();
		customer.setCustomerId(1L);
		customer.setFirstName("kavya");
		customer.setLastName("kinthali");
		customer.setEmail("kavya@gmail.com");
		customer.setPassword("kavya");
		customer.setPanNo("323547345");

		SavingsAccount savingsAccount = new SavingsAccount();
		savingsAccount.setAccountNumber((long) 23453245);
		savingsAccount.setAccountBalance(64323L);
		customer.setSavingsAccount(savingsAccount);

		Mockito.when(customerRepository.save(Mockito.any(Customer.class))).thenReturn((customer1));
		Customer resCustomer = customerService.customerRegistration(customer);
		Assert.assertNotNull(resCustomer);
		Assert.assertEquals(customer.getPassword(),customer1.getPassword());
	}
	
	@Test(expected =Exception.class)
	public void testSaveCustomerForExce() {
		Customer customer = new Customer();
		customer.setCustomerId(1L);
		customer.setFirstName("kavya");
		customer.setLastName("kinthali");
		SavingsAccount savingsAccount = new SavingsAccount();
		savingsAccount.setAccountNumber((long) 23453245);
		customer.setSavingsAccount(savingsAccount);
	
		Mockito.when(customerRepository.save(Mockito.any(Customer.class))).thenThrow(Exception.class);
		Customer resCustomer = customerService.customerRegistration(customer);
	}

	@Test
	public void TestLogin() {
		Customer customer = new Customer();
		customer.setCustomerId(1L);
		customer.setFirstName("kavya");
		customer.setLastName("kinthali");
		customer.setEmail("kavya@gmail.com");

		Mockito.when(customerRepository.findByEmailAndPassword("kavya@gmail.com", "kavya")).thenReturn(customer);
		Customer customer2 = customerService.customerLogin("kavya@gmail.com", "kavya");
		Assert.assertNotNull(customer2);
		Assert.assertEquals(customer.getEmail(),"kavya@gmail.com");
	}
	
	@Test(expected =Exception.class)
	public void TestLoginForExce() {

		Customer customer = new Customer();
		Mockito.when(customerRepository.findByEmailAndPassword("kavya@gmail.com", "kavya")).thenThrow(Exception.class);
		Customer customer2 = customerService.customerLogin("kavya@gmail.com", "kavya");
		Assert.assertNotNull(customer2);
		Assert.assertEquals(customer.getEmail(),"kavya@gmail.com");
	}
	
}
