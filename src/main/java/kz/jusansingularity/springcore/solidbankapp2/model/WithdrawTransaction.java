package kz.jusansingularity.springcore.solidbankapp2.model;

public class WithdrawTransaction extends Transaction{
    public WithdrawTransaction(TransactionType transactionType,
                               String id,
                               String clientID,
                               double amount,
                               String accountID,
                               String accountType,
                               double balanceBeforeTransaction,
                               double balanceAfterTransaction) {

        super(transactionType, id, clientID, amount, accountID, accountType, balanceBeforeTransaction, balanceAfterTransaction);
    }
}
