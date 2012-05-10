package pl.softmil.validator.el.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;

import pl.softmil.validator.el.constraint.SpelAssert;

public class SpelAssertValidator implements
        ConstraintValidator<SpelAssert, Object> {
    @Autowired
    private ExpressionParser expressionParser;
    private String[] fieldErrors;
    private String message;

    private Expression expression;

    @Override
    public void initialize(SpelAssert constraintAnnotation) {
        expression = expressionParser.parseExpression(constraintAnnotation
                .value());
        fieldErrors = constraintAnnotation.errorFields();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        boolean isExpressionTrue = expressionTrue(value);
        if (!isExpressionTrue && registerFieldErrors()) {
            doRegisterFieldErrors(context);
        }
        return isExpressionTrue;
    }

    private void doRegisterFieldErrors(ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        for (String fieldError : fieldErrors) {
            context.buildConstraintViolationWithTemplate(message)
                    .addNode(fieldError).addConstraintViolation();
        }
    }

    private boolean registerFieldErrors() {
        return fieldErrors.length > 0;
    }

    private boolean expressionTrue(Object value) {
        return Boolean.TRUE.equals(expression.getValue(value, Boolean.class));
    }

}
