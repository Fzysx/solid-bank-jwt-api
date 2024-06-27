package kz.jusansingularity.springcore.solidbankapp2.DTO;

import kz.jusansingularity.springcore.solidbankapp2.model.TransactionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReturningTransactionDTO {
    private String id;
    private double amount;
    private String ownerAccountId;
    private TransactionType transactionType;
    private String accountFromId;
    private String accountToId;
    private double balanceBeforeTransaction;
    private double  balanceAfterTransaction;
}
