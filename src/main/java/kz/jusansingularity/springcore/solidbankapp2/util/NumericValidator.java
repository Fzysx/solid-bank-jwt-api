package kz.jusansingularity.springcore.solidbankapp2.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NumericValidator implements ConstraintValidator<Numeric, String> {
    @Override
    public void initialize(Numeric constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null){
            return true;
        }
        if(value.isEmpty()){
            return true;
        }
        if(value.equals("0")){
            return false;
        }

        try {
            Double.parseDouble(value);
            // Если преобразование прошло успешно, проверим, не содержит ли строка буквы
            if (value.matches("[0-9]+\\.?[0-9]*")) {
                return true; // Все символы являются числами
            } else {
                return false; // Строка содержит буквенные символы
            }
        } catch (NumberFormatException e) {
            return false; // Не удалось преобразовать значение в число
        }
    }
}
