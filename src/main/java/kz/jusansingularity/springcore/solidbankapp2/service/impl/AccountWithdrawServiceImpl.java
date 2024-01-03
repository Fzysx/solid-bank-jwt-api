package kz.jusansingularity.springcore.solidbankapp2.service.impl;

import kz.jusansingularity.springcore.solidbankapp2.DAO.AccountDAO;
import kz.jusansingularity.springcore.solidbankapp2.model.AccountWithdraw;
import kz.jusansingularity.springcore.solidbankapp2.service.AccountWithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountWithdrawServiceImpl implements AccountWithdrawService {
    AccountDAO accountDAO;

    @Autowired
    public AccountWithdrawServiceImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public void withdraw(AccountWithdraw account, double amount) {
        account.setBalance(account.getBalance() - amount);
        System.out.println(amount + "$ transferred from " + account + " account");
        accountDAO.updateAccount(account);
    }
}
