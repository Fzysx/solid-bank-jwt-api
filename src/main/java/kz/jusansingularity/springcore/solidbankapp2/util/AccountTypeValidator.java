package kz.jusansingularity.springcore.solidbankapp2.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class AccountTypeValidator implements ConstraintValidator<AccountType, String> {

    private final List<String> allowedAccountTypes = Arrays.asList("FIXED", "SAVING", "CHECKING");

    @Override
    public void initialize(AccountType constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null){
            return true;
        }
        if(value.isEmpty()){
            return true;
        }
        return allowedAccountTypes.contains(value.toUpperCase());
    }

}
