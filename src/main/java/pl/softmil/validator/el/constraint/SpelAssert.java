package pl.softmil.validator.el.constraint;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.*;

import javax.validation.*;

import pl.softmil.validator.el.impl.SpelAssertValidator;

@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SpelAssertValidator.class)
@Documented
public @interface SpelAssert {
    String message() default "{aaaaa}";

    String[] errorFields() default {};

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String value();

    @Target({ ANNOTATION_TYPE, TYPE })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        SpelAssert[] value();
    }
}
