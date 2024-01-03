package kz.jusansingularity.springcore.solidbankapp2.model;

public class CheckingAccount extends AccountWithdraw{
    public CheckingAccount(AccountType accountType, String id, String clientID, double balance, boolean withdrawAllowed){
        super(accountType, id, clientID, balance, withdrawAllowed);
    }
}
