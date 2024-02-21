package kz.jusansingularity.springcore.solidbankapp2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Account")
public class Account {

    @Id
    @Column(name = "id")
    private String id;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(name = "client_id")
    private String clientID;

    @Column(name = "balance")
    private double balance;

    @Column(name = "is_withdraw_allowed")
    private boolean withdrawAllowed;

    @OneToMany(mappedBy = "ownerAccount")
    private List<Transaction> transactions;

    public Account(AccountType accountType, String id, String clientID, double balance, boolean withdrawAllowed){
        this.accountType = accountType;
        this.id = String.format("%03d%06d", 1, Integer.valueOf(id));
        this.clientID = clientID;
        this.balance = balance;
        this.withdrawAllowed = withdrawAllowed;
    }
    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", accountType=" + accountType +
                ", clientID='" + clientID + '\'' +
                ", balance=" + balance +
                ", withdrawAllowed=" + withdrawAllowed +
                '}';
    }
}
