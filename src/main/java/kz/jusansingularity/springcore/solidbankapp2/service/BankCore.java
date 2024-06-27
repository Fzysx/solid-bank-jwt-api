package kz.jusansingularity.springcore.solidbankapp2.service;

import kz.jusansingularity.springcore.solidbankapp2.model.AccountType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankCore {
    private static long id = 1;
    private long lastAccountNumber = 1;
    private final AccountCreationService accountCreation;

    public String createNewAccount(AccountType accountType, String clientID){
        String accountId = accountCreation.create(accountType, id, clientID, lastAccountNumber);
        incrementLastAccountNumber();

        return accountId;
    }
    private void incrementLastAccountNumber(){
        lastAccountNumber++;

    }
}
