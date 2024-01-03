package kz.jusansingularity.springcore.solidbankapp2.model;

public class DepositTransaction extends Transaction{
    public DepositTransaction(TransactionType transactionType,
                              String id,
                              String clientID,
                              double amount,
                              String accountID,
                              double balanceBeforeTransaction,
                              double balanceAfterTransaction) {

        super(transactionType, id, clientID, amount, accountID, balanceBeforeTransaction, balanceAfterTransaction);
    }
}
