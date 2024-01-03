package kz.jusansingularity.springcore.solidbankapp2.service;

import java.util.List;

import kz.jusansingularity.springcore.solidbankapp2.model.Account;
import kz.jusansingularity.springcore.solidbankapp2.model.AccountType;
import kz.jusansingularity.springcore.solidbankapp2.model.AccountWithdraw;

public interface AccountListingService {
    Account getClientAccount(String clientID, String accountID);
    AccountWithdraw getClientWithdrawAccount(String clientID, String accountID);
    List<Account> getClientAccounts(String clientID);
    List<Account> getClientAccountsByType(String clientID, AccountType accountType);
}
