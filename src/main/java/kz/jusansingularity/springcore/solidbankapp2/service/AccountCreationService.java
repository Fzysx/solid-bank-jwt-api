package kz.jusansingularity.springcore.solidbankapp2.service;

import kz.jusansingularity.springcore.solidbankapp2.model.AccountType;

public interface AccountCreationService {
    String create(AccountType accountType, long bankID, String clientID, long accountID);


}
