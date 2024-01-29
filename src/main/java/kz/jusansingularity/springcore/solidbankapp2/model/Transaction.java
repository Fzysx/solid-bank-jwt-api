package kz.jusansingularity.springcore.solidbankapp2.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Table(name = "Transaction")
@NoArgsConstructor
public class Transaction {

    @Id
    @Column(name = "id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account owner;

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

    public Transaction(TransactionType transactionType, String id, String clientID ,double amount, Account owner,
                       double balanceBeforeTransaction, double balanceAfterTransaction) {
        this.transactionType = transactionType;

        if(transactionType.equals(TransactionType.DEPOSIT)) {
            this.id = String.format("%01d%08d", 1, Integer.valueOf(id));
        } else if(transactionType.equals(TransactionType.WITHDRAW)){
            this.id = String.format("%01d%08d", 2, Integer.valueOf(id));
        }

        this.clientID = clientID;
        this.amount = amount;
        this.owner = owner;
        this.balanceBeforeTransaction = balanceBeforeTransaction;
        this.balanceAfterTransaction = balanceAfterTransaction;
    }
}
