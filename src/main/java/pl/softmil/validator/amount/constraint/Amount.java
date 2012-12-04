package pl.softmil.validator.amount.constraint;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

@Documented
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Digits(fraction = 0, integer = 0)
@DecimalMin(value = "0")
@Constraint(validatedBy = {})
public @interface Amount {
    @OverridesAttribute.List({ @OverridesAttribute(constraint = Digits.class, name = "message") })
    String message() default "{invalid fraction amount}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @OverridesAttribute.List({ @OverridesAttribute(constraint = Digits.class, name = "integer") })
    int integer() default 8;
    
    @OverridesAttribute.List({ @OverridesAttribute(constraint = DecimalMin.class, name = "value") })
    String min() default "0";
}
