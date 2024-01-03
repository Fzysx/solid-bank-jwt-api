package kz.jusansingularity.springcore.solidbankapp2.model;

public class Account {
    private AccountType accountType;
    private String id;
    private String clientID;
    private double balance;
    private boolean withdrawAllowed;


    public Account(AccountType accountType, String id, String clientID, double balance, boolean withdrawAllowed){
        this.accountType = accountType;
        this.id = String.format("%03d%06d", 1, Integer.valueOf(id));
        this.clientID = clientID;
        this.balance = balance;
        this.withdrawAllowed = withdrawAllowed;
    }


    @Override
    public String toString(){
        return "Account{" +
                "accountType=" + accountType +
                ", id='" + id + '\'' +
                ", clientId='" + clientID + '\'' +
                ", balance=" + balance +
                ", withdrawAllowed=" + withdrawAllowed +
                '}';
    }

    public String getClientID(){
        return clientID;
    }

    public void setClientID(String clientID){
        this.clientID = clientID;
    }

    public boolean isWithdrawAllowed(){
        return withdrawAllowed;
    }

    public void setWithdrawAllowed(boolean withdrawAllowed){
        this.withdrawAllowed = withdrawAllowed;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    void setAccountType(AccountType accountType){
        this.accountType = accountType;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

}
