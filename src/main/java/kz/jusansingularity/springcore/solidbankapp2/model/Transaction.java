package kz.jusansingularity.springcore.solidbankapp2.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Transaction {
    private TransactionType transactionType;
    private String id;
    private String clientID;
    private double amount;
    private String accountID;

    private double balanceBeforeTransaction;
    private double  balanceAfterTransaction;

    public Transaction(TransactionType transactionType, String id, String clientID, double amount, String accountID,
                       double balanceBeforeTransaction, double balanceAfterTransaction) {
        this.transactionType = transactionType;

        if(transactionType.equals(TransactionType.DEPOSIT)) {
            this.id = String.format("%01d%08d", 1, Integer.valueOf(id));
        } else if(transactionType.equals(TransactionType.WITHDRAW)){
            this.id = String.format("%01d%08d", 2, Integer.valueOf(id));
        }

        this.clientID = clientID;
        this.amount = amount;
        this.accountID = accountID;
        this.balanceBeforeTransaction = balanceBeforeTransaction;
        this.balanceAfterTransaction = balanceAfterTransaction;
    }


    /*@Override
    public String toString() {
        return "Transaction{" +
                "transactionType=" + transactionType +
                ", id='" + id + '\'' +
                ", clientID='" + clientID + '\'' +
                ", amount=" + amount +
                ", accountID='" + accountID + '\'' +
                ", balanceBeforeTransaction=" + balanceBeforeTransaction +
                ", balanceAfterTransaction=" + balanceAfterTransaction +
                '}';
    }*/

   /* public TransactionType getTransactionType() {
        return transactionType;
    }

    public String getId() {
        return id;
    }

    public String getClientID() {
        return clientID;
    }

    public double getAmount() {
        return amount;
    }

    public String getAccountID() {
        return accountID;
    }

    public double getBalanceBeforeTransaction() {
        return balanceBeforeTransaction;
    }

    public double getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public void setBalanceBeforeTransaction(int balanceBeforeTransaction) {
        this.balanceBeforeTransaction = balanceBeforeTransaction;
    }

    public void setBalanceAfterTransaction(int balanceAfterTransaction) {
        this.balanceAfterTransaction = balanceAfterTransaction;
    }*/
}
