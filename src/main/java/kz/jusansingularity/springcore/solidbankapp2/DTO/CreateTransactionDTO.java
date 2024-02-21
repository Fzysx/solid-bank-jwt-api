package kz.jusansingularity.springcore.solidbankapp2.DTO;



import jakarta.validation.constraints.NotEmpty;
import kz.jusansingularity.springcore.solidbankapp2.util.Numeric;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTransactionDTO {

    @Numeric (message = "Invalid amount value. Input valid amount value, please")
    @NotEmpty(message = "Amount value should not be empty")
    private String amount;
}
