package kz.jusansingularity.springcore.solidbankapp2.DAO;

import java.util.ArrayList;
import java.util.List;
import kz.jusansingularity.springcore.solidbankapp2.model.Account;
import kz.jusansingularity.springcore.solidbankapp2.model.AccountType;
import kz.jusansingularity.springcore.solidbankapp2.model.AccountWithdraw;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryAccountDao implements AccountDAO{

    private List<Account> accountList = new ArrayList<>();

    @Override
    public List<Account> getClientAccounts(String clientID){

        List<Account> returningAccounts = new ArrayList<>();

        for(Account account: this.accountList){
            if(account.getClientID().equals(clientID) ){
                returningAccounts.add(account);
            }
        }

        return returningAccounts;
    }
    @Override
    public void createNewAccount(Account account){
        this.accountList.add(account);
        System.out.println("Bank account created successfully");
    }
    @Override
    public void updateAccount(Account account){

    }
    @Override
    public List<Account> getClientAccountsByType(String clientID, AccountType accountType){
        return null;
    }
    @Override
    public AccountWithdraw getClientWithdrawAccount(String clientID, String accountID){
        if(accountList != null){
            for(Account account: this.accountList){
                if(account.isWithdrawAllowed() && account.getId().equals(accountID) ){
                    return (AccountWithdraw)account;
                }
            }
        }
        return null;
    }
    @Override
    public Account getClientAccount(String clientID, String accountID){
        if(accountList != null){
            for(Account account: this.accountList){
                if(account.getId().equals(accountID) ){
                    return account;
                }
            }
        }
        return null;
    }
}
