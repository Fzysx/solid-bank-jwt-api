package kz.jusansingularity.springcore.solidbankapp2.DAO;

import kz.jusansingularity.springcore.solidbankapp2.model.Account;
import kz.jusansingularity.springcore.solidbankapp2.model.AccountType;
import kz.jusansingularity.springcore.solidbankapp2.model.AccountWithdraw;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountWithdrawMapper implements RowMapper<Account> {


    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        AccountWithdraw accountWithdraw = new AccountWithdraw();

        accountWithdraw.setId(rs.getString("id"));
        accountWithdraw.setAccountType(AccountType.valueOf(rs.getString("account_type")));
        accountWithdraw.setClientID(rs.getString("client_id"));
        accountWithdraw.setBalance(rs.getDouble("balance"));
        accountWithdraw.setWithdrawAllowed(rs.getBoolean("is_withdraw_allowed"));

        return accountWithdraw;
    }
}
