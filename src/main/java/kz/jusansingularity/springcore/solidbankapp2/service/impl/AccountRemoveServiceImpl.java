package kz.jusansingularity.springcore.solidbankapp2.service.impl;

import kz.jusansingularity.springcore.solidbankapp2.DAO.AccountDAO;
import kz.jusansingularity.springcore.solidbankapp2.model.Account;
import kz.jusansingularity.springcore.solidbankapp2.service.AccountRemoveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountRemoveServiceImpl implements AccountRemoveService {

    private final AccountDAO accountDAO;


    @Override
    public void remove(Account account) {
        accountDAO.removeAccount(account);
    }
}
