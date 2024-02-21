package kz.jusansingularity.springcore.solidbankapp2.DTO;

import jakarta.persistence.*;
import kz.jusansingularity.springcore.solidbankapp2.model.AccountType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReturningAccountDTO {

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
}
