package kz.jusansingularity.springcore.solidbankapp2.model;

import kz.jusansingularity.springcore.solidbankapp2.service.AccountListingService;
import kz.jusansingularity.springcore.solidbankapp2.service.BankCore;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AccountBasicCLI {
    private CreateAccountOperationUI createAccountOperationUI;
    private BankCore bankCore;
    private AccountListingService accountListing;

    /*@Autowired
    public AccountBasicCLI(CreateAccountOperationUI createAccountOperationUI, BankCore bankCore, AccountListingService accountListing){
        this.createAccountOperationUI = createAccountOperationUI;
        this.bankCore = bankCore;
        this.accountListing = accountListing;
    }*/

    public void createAccountRequest(String clientID) throws Exception {
        bankCore.createNewAccount(createAccountOperationUI.requestAccountType(), clientID);

    }

    public void getAccounts(String clientID){
        if(accountListing.getClientAccounts(clientID).size() > 0){
            for(Account account: accountListing.getClientAccounts(clientID)){
                System.out.println(account);
            }
        } else {
            System.out.println(accountListing.getClientAccounts(clientID));
        }


    }
}
