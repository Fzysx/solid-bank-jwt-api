package kz.jusansingularity.springcore.solidbankapp2.model;

public class WithdrawTransaction extends Transaction{
    public WithdrawTransaction(TransactionType transactionType,
                               String id,
                               String clientID,
                               double amount,
                               String accountID,
                               double balanceBeforeTransaction,
                               double balanceAfterTransaction) {

        super(transactionType, id, clientID, amount, accountID, balanceBeforeTransaction, balanceAfterTransaction);
    }
}
