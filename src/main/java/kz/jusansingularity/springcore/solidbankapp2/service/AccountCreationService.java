package kz.jusansingularity.springcore.solidbankapp2.service;

import kz.jusansingularity.springcore.solidbankapp2.model.AccountType;

public interface AccountCreationService {
    void create(AccountType accountType, long bankID, String clientID, long accountID);


}
