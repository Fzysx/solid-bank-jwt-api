package kz.jusansingularity.springcore.solidbankapp2.service;

import kz.jusansingularity.springcore.solidbankapp2.model.AccountType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankCore {
    private static long id = 1;
    private long lastAccountNumber = 1;
    private AccountCreationService accountCreation;

    @Autowired
    public BankCore(AccountCreationService accountCreation){
        this.accountCreation = accountCreation;
    }

    public void createNewAccount(AccountType accountType, String clientID){
        accountCreation.create(accountType, id, clientID, lastAccountNumber);
        incrementLastAccountNumber();
    }
    private void incrementLastAccountNumber(){
        lastAccountNumber++;

    }
}
