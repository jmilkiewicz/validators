package pl.softmil.validator.conditional.impl;

import javax.validation.ConstraintValidator;

import pl.softmil.validator.conditional.constraint.FieldNotNullIf;

public class FieldNotNullIfValidator extends IfValidator implements
        ConstraintValidator<FieldNotNullIf, Object> {
    private String expressionString;

    private String field;

    private String message;

    @Override
    public void initialize(FieldNotNullIf constraintAnnotation) {
        field = constraintAnnotation.field();
        expressionString = constraintAnnotation.expression();
        message = constraintAnnotation.message();
    }

    @Override
    protected String getExpressionString() {
        return expressionString;
    }

    @Override
    protected String getField() {
        return field;
    }

    @Override
    protected String getMessage() {
        return message;
    }

    @Override
    protected boolean isPropertyValid(Object propertyValue) {
        return propertyValue != null;
    }

}
