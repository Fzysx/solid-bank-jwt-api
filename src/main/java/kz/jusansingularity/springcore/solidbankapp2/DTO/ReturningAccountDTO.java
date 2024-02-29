package kz.jusansingularity.springcore.solidbankapp2.DTO;

import kz.jusansingularity.springcore.solidbankapp2.model.AccountType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReturningAccountDTO {
    private String id;
    private AccountType accountType;
    private String clientID;
    private double balance;
    private boolean withdrawAllowed;
}
