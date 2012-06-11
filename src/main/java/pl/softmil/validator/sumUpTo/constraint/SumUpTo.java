package pl.softmil.validator.sumUpTo.constraint;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.*;

import javax.validation.*;

import pl.softmil.validator.sumUpTo.impl.SumUpToValidator;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = SumUpToValidator.class)
public @interface SumUpTo {
    
    String message() default "{org.hibernate.validator.constraints.SumUpTo.message}";

    Class<?>[] groups() default {};
     
    String targetSum() default "100";
         
    Class<? extends Payload>[] payload() default {};

    @Target({ TYPE, ANNOTATION_TYPE })
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        SumUpTo[] value();
    }

}
