package kz.jusansingularity.springcore.solidbankapp2.DAO;

import kz.jusansingularity.springcore.solidbankapp2.model.Account;
import kz.jusansingularity.springcore.solidbankapp2.model.AccountType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapper implements RowMapper<Account> {


    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Account account = new Account();

        account.setId(rs.getString("id"));
        account.setAccountType(AccountType.valueOf(rs.getString("account_type")));
        account.setClientID(rs.getString("client_id"));
        account.setBalance(rs.getDouble("balance"));
        account.setWithdrawAllowed(rs.getBoolean("is_withdraw_allowed"));

        return account;
    }
}
