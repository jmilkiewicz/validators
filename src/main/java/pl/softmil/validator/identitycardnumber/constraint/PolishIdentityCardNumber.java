package pl.softmil.validator.identitycardnumber.constraint;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.*;

import javax.validation.*;

import pl.softmil.validator.identitycardnumber.impl.PolishIdentityCardNumberValidator;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = PolishIdentityCardNumberValidator.class)
public @interface PolishIdentityCardNumber {
    String message() default "{polishdocumentnumber.invalid.checksum}";

    Class<?>[] groups() default {};
     
    Class<? extends Payload>[] payload() default {};
}
