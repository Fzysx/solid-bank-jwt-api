package kz.jusansingularity.springcore.solidbankapp2.util;


public class AccountNotFoundException extends RuntimeException{
    private String accountID;

    public AccountNotFoundException(String accountID){
        this.accountID = accountID;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }
}
