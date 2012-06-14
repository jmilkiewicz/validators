package pl.softmil.validator.pesel.constraint;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.*;

import javax.validation.*;

import pl.softmil.validator.pesel.impl.PeselValidator;


@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = PeselValidator.class)
public @interface Pesel {
    String message() default "{org.hibernate.validator.constraints.pesel.invalid}";

    Class<?>[] groups() default {};
     
    Class<? extends Payload>[] payload() default {};

    @Target({ TYPE, ANNOTATION_TYPE })
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        Pesel[] value();
    }
}
