package com.example.BankingApplication1.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name ="savingsAccount" )
public class SavingsAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long accountNumber;
	private long accountBalance;

	  @OneToOne(mappedBy = "savingsAccount",cascade = CascadeType.ALL) 
	  private Customer customer;

	public Long getAccountNumber() {
		return accountNumber;
	}

	


	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public long getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(long accountBalance) {
		this.accountBalance = accountBalance;
	}

}
