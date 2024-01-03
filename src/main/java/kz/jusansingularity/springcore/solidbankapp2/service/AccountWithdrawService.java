package kz.jusansingularity.springcore.solidbankapp2.service;
import kz.jusansingularity.springcore.solidbankapp2.model.AccountWithdraw;

public interface AccountWithdrawService {
    void withdraw(AccountWithdraw account, double amount);
}
