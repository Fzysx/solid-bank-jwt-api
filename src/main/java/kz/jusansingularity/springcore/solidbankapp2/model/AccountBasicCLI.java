package kz.jusansingularity.springcore.solidbankapp2.model;

import kz.jusansingularity.springcore.solidbankapp2.service.AccountListingService;
import kz.jusansingularity.springcore.solidbankapp2.service.BankCore;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class AccountBasicCLI {
    private CreateAccountOperationUI createAccountOperationUI;
    private BankCore bankCore;
    private AccountListingService accountListing;

    public void createAccountRequest(String clientID) {
        try {
            bankCore.createNewAccount(createAccountOperationUI.requestAccountType(), clientID);
        } catch(Exception e){
            System.out.println("Repeat the operations, please.");
        }
    }

    public void getAccounts(String clientID){
        List<Account> accounts = new ArrayList<>();
        accounts = accountListing.getClientAccounts(clientID);

        if(!accounts.isEmpty()){
            for(Account account: accounts){
                System.out.println(account);
            }
        } else {
            System.out.println(accounts);
        }
    }
}
