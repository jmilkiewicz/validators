package pl.softmil.validator.roi.constraint;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.*;

import javax.validation.*;
import javax.validation.constraints.*;

@Documented
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Digits(fraction = 0, integer = 0)
@DecimalMin(value = "0.1")
@Constraint(validatedBy = {})
public @interface Roi {
    @OverridesAttribute.List({ @OverridesAttribute(constraint = Digits.class, name = "message") })
    String message() default "{invalid fraction amount}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @OverridesAttribute.List({ @OverridesAttribute(constraint = Digits.class, name = "integer") })
    int integer() default 2;
    
    @OverridesAttribute.List({ @OverridesAttribute(constraint = Digits.class, name = "fraction") })
    int fraction() default 1;

}
