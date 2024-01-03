package kz.jusansingularity.springcore.solidbankapp2.service;

import kz.jusansingularity.springcore.solidbankapp2.model.Account;

public interface AccountDepositService {
    void deposit(Account account, double amount);
}
