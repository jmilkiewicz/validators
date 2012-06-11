package pl.softmil.validator.conditional.constraint;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.*;

import javax.validation.*;

import pl.softmil.validator.conditional.impl.*;

@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = ValidatePropertyIfValidator.class)
public @interface ValidatePropertyIf {
    String message() default "_DO_NOT_USE_";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String expression();

    String field();
    
    Class<?>[] fieldValidationGroup() default {ValidatePropertyIfGroup.class};

    @Target({ TYPE, ANNOTATION_TYPE })
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        ValidatePropertyIf[] value();
    }
    
    
    public interface ValidatePropertyIfGroup{
        
    }
}
