package kz.jusansingularity.springcore.solidbankapp2.service.impl;

import kz.jusansingularity.springcore.solidbankapp2.DAO.AccountDAO;
import kz.jusansingularity.springcore.solidbankapp2.model.Account;
import kz.jusansingularity.springcore.solidbankapp2.service.AccountDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountDepositServiceImpl implements AccountDepositService {

    AccountDAO accountDAO;

    @Autowired
    public AccountDepositServiceImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public void deposit(Account account, double amount) {
        account.setBalance(account.getBalance() + amount);
        System.out.println(amount + "$ transferred to " + account + " account");
        accountDAO.updateAccount(account);
    }
}
