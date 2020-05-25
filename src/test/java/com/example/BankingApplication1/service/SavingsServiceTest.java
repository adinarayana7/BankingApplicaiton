package com.example.BankingApplication1.service;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.BankingApplication1.exceptions.BeneficiaryAccountNotFound;
import com.example.BankingApplication1.exceptions.InsufficientBalance;
import com.example.BankingApplication1.model.BeneficiaryAccount;
import com.example.BankingApplication1.model.SavingsAccount;
import com.example.BankingApplication1.repository.SavingsRepository;

@RunWith(MockitoJUnitRunner.class)
public class SavingsServiceTest {
	

    @InjectMocks
    SavingsService savingsService;

 

    @Mock
    SavingsRepository savingsRepository;
    
    
    @Mock
    BeneficiaryAccountService beneficiaryAccountService;
    
    SavingsAccount savingsAccount =null;
    
    @BeforeClass
    public static void setup() {
        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.setAccountNumber((long) 5465);
        savingsAccount.setAccountBalance(50000);
        
        BeneficiaryAccount beneficiaryAccount =new BeneficiaryAccount();
        beneficiaryAccount.setAccountBalance(45000);
        beneficiaryAccount.setAccountNumber(2426);
        beneficiaryAccount.setName("gsp");
        beneficiaryAccount.setPhone("9087654321");
        beneficiaryAccount.setEmail("gs@gmail.com");
        beneficiaryAccount.setDescription("savings account");
    }


    @Test(expected=NullPointerException.class)
    public void testGetSavingsAccountForException() {

        if(savingsAccount.getAccountBalance()>45000L) {
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance() - 45000L);
            BeneficiaryAccount beneficiaryAccount;
            try {
                
             beneficiaryAccount = beneficiaryAccountService.getBeneficiaryAccount(2426L);
            }
            catch (Exception e){
                throw new BeneficiaryAccountNotFound();
                
            }
            
            beneficiaryAccount.setAccountBalance(beneficiaryAccount.getAccountBalance() + 45000L);
            beneficiaryAccountService.updateBeneficiaryAccountRepository(beneficiaryAccount);
            savingsRepository.save(savingsAccount);
        }
        else
        {
            throw new InsufficientBalance();
        }
        
    }


}
