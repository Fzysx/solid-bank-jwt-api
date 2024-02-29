package kz.jusansingularity.springcore.solidbankapp2.DTO;

import jakarta.validation.constraints.NotEmpty;
import kz.jusansingularity.springcore.solidbankapp2.util.AccountType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccountDTO {

    @NotEmpty(message = "Account type should not be empty. Please choose account type: Fixed, Saving, Checking")
    @AccountType(message = "Account type is not valid. Please choose account type: Fixed, Saving, Checking")
    private String accountType;

}
