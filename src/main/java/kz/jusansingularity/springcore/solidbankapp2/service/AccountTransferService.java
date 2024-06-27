package kz.jusansingularity.springcore.solidbankapp2.service;

import kz.jusansingularity.springcore.solidbankapp2.model.Account;
import kz.jusansingularity.springcore.solidbankapp2.model.AccountWithdraw;

public interface AccountTransferService {
    void transfer(AccountWithdraw accountFrom, Account AccountTo, double amount);
}
