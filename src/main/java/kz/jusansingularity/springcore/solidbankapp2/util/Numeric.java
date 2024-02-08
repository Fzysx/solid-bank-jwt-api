package kz.jusansingularity.springcore.solidbankapp2.util;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NumericValidator.class)
@Documented
public @interface Numeric {
    String message() default "Invalid numeric value";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
