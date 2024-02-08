package kz.jusansingularity.springcore.solidbankapp2.DAO;

import java.util.ArrayList;
import java.util.List;
import kz.jusansingularity.springcore.solidbankapp2.model.Account;
import kz.jusansingularity.springcore.solidbankapp2.model.AccountType;
import kz.jusansingularity.springcore.solidbankapp2.model.AccountWithdraw;
import kz.jusansingularity.springcore.solidbankapp2.util.AccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryAccountDao implements AccountDAO{

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public MemoryAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Account> getClientAccounts(String clientID){

        String sql = "SELECT * FROM Account WHERE client_id = ?";
        return jdbcTemplate.query(sql, new Object[]{clientID}, new AccountMapper());
    }

    @Override
    public String createNewAccount(Account account){
        String sql = "INSERT INTO Account(id, account_type, client_id, balance, is_withdraw_allowed) VALUES ( ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, account.getId() , account.getAccountType().getCode(), account.getClientID(), account.getBalance(), account.isWithdrawAllowed());
        System.out.println("Bank account id = " + account.getId() + " created successfully");
        return account.getId();
    }
    @Override
    public void updateAccount(Account account){
        String sql = "UPDATE Account SET balance = ? WHERE id = ?";
        jdbcTemplate.update(sql, account.getBalance(), account.getId());
        System.out.println("Bank account id = " + account.getId() + " updated successfully");
    }

    @Override
    public void removeAccount(Account account) {
        String sql = "DELETE FROM Account WHERE id = ?";
        jdbcTemplate.update(sql, account.getId());
        System.out.println("Bank removed updated successfully");
    }

    @Override
    public List<Account> getClientAccountsByType(String clientID, AccountType accountType){
        return null;
    }
    @Override
    public AccountWithdraw getClientWithdrawAccount(String clientID, String accountID) {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM Account WHERE client_id = ? AND id = ? AND is_withdraw_allowed = true";
        accounts = jdbcTemplate.query(sql, new Object[]{clientID, accountID}, new AccountWithdrawMapper());

        if (!accounts.isEmpty()) {
            Account account = accounts.get(0);
            return (AccountWithdraw) account;
        } else {
            throw new AccountNotFoundException();
        }
    }
    @Override
    public Account getClientAccount(String clientID, String accountID) {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM Account WHERE client_id = ? AND id = ?";
        accounts = jdbcTemplate.query(sql, new Object[]{clientID, accountID}, new AccountMapper());

        if (!accounts.isEmpty()) {
            return accounts.get(0);
        } else {
            throw new AccountNotFoundException();
        }
    }
}
