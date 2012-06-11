package pl.softmil.validator.conditional.impl;

import java.util.Set;

import javax.validation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.*;

import pl.softmil.validator.conditional.constraint.*;

public class ValidatePropertyIfValidator implements
        ConstraintValidator<ValidatePropertyIf, Object> {
    @Autowired
    private ExpressionParser expressionParser;

    @Autowired
    private Validator validator;

    private String expressionString;

    private String field;

    private Class<?>[] fieldValidationGroups;

    @Override
    public void initialize(ValidatePropertyIf constraintAnnotation) {
        expressionString = constraintAnnotation.expression();
        field = constraintAnnotation.field();
        fieldValidationGroups = constraintAnnotation.fieldValidationGroup();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        boolean result = true;
        if (isExpressionTrue(value)) {
            Set<ConstraintViolation<Object>> fieldConstraintViolations = validateField(value);
            if (!fieldConstraintViolations.isEmpty()) {
                recordValidationErrors(fieldConstraintViolations, context);
                result = false;
            }
        }
        return result;
    }

    private void recordValidationErrors(
            Set<ConstraintViolation<Object>> fieldConstraintViolations,
            ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        for (ConstraintViolation<Object> constraintViolation : fieldConstraintViolations) {
            context.buildConstraintViolationWithTemplate(
                    constraintViolation.getMessage())
                    .addNode(constraintViolation.getPropertyPath().toString())
                    .addConstraintViolation();
        }

    }

    private Set<ConstraintViolation<Object>> validateField(Object value) {
        return validator.validateProperty(value, field, fieldValidationGroups);
    }

    private boolean isExpressionTrue(Object value) {
        Expression expression = expressionParser
                .parseExpression(expressionString);
        return Boolean.TRUE.equals(expression.getValue(value, Boolean.class));
    }

}
