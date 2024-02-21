package kz.jusansingularity.springcore.solidbankapp2.DAO;

import java.util.List;
import kz.jusansingularity.springcore.solidbankapp2.model.Account;
import kz.jusansingularity.springcore.solidbankapp2.model.AccountType;
import kz.jusansingularity.springcore.solidbankapp2.model.AccountWithdraw;

public interface AccountDAO {
    List<Account> getClientAccounts(String clientID);
    String createNewAccount(Account account);
    void updateAccount(Account account);
    void removeAccount(Account account);
    List<Account> getClientAccountsByType(String clientID, AccountType accountType);
    AccountWithdraw getClientWithdrawAccount(String clientID, String accountID);
    Account getClientAccount(String clientID, String accountID);
}
