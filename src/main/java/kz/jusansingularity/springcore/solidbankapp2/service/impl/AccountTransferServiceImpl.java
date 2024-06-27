package kz.jusansingularity.springcore.solidbankapp2.service.impl;

import kz.jusansingularity.springcore.solidbankapp2.model.Account;
import kz.jusansingularity.springcore.solidbankapp2.model.AccountWithdraw;
import kz.jusansingularity.springcore.solidbankapp2.service.AccountDepositService;
import kz.jusansingularity.springcore.solidbankapp2.service.AccountTransferService;
import kz.jusansingularity.springcore.solidbankapp2.service.AccountWithdrawService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountTransferServiceImpl implements AccountTransferService {
    private final AccountDepositService accountDepositService;
    private final AccountWithdrawService accountWithdrawService;

    @Override
    public void transfer(AccountWithdraw accountFrom, Account accountTo, double amount) {
        accountWithdrawService.withdraw(accountFrom, amount);
        accountDepositService.deposit(accountTo, amount);
    }
}
