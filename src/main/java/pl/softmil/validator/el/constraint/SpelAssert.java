package pl.softmil.validator.el.constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import pl.softmil.validator.el.impl.SpelAssertValidator;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SpelAssertValidator.class)
@Documented
public @interface SpelAssert {
    String message() default "{aaaaa}";

    String[] errorFields() default {};

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String value();
}
