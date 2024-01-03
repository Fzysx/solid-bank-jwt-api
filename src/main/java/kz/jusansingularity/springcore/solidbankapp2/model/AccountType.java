package kz.jusansingularity.springcore.solidbankapp2.model;

public enum AccountType {

    FIXED("FIXED"),
    SAVING("SAVING"),
    CHECKING("CHECKING");
    private String accountType;
    AccountType(String accountType){
        this.accountType = accountType;
    }
    public String getCode(){ return accountType;}

}
