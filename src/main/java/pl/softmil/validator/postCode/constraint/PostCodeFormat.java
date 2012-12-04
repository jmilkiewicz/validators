package pl.softmil.validator.postCode.constraint;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

@Documented
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Pattern(regexp = "[0-9]{2}-[0-9]{3}")
@Constraint(validatedBy = {})
public @interface PostCodeFormat {
    @OverridesAttribute.List({ @OverridesAttribute(constraint = Pattern.class, name = "message") })
    String message() default "{invalid post code format}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
