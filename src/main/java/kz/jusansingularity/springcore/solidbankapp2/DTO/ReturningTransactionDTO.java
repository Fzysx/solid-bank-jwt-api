package kz.jusansingularity.springcore.solidbankapp2.DTO;

import jakarta.persistence.*;
import kz.jusansingularity.springcore.solidbankapp2.model.TransactionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReturningTransactionDTO {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "client_id")
    private String clientID;

    @Column(name = "amount")
    private double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "balance_before")
    private double balanceBeforeTransaction;

    @Column(name = "balance_after")
    private double  balanceAfterTransaction;
}
