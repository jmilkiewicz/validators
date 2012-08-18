package pl.softmil.validator.share.constraint;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.*;

import javax.validation.*;
import javax.validation.constraints.*;

@Documented
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@DecimalMin(value = "1")
@DecimalMax(value = "100")
@Digits(integer = 3, fraction = 0, message = "{javax.validation.constraints.Integer}")
@Constraint(validatedBy = {})
public @interface Share {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
