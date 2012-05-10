package pl.softmil.validator.conditional.impl;

import javax.validation.ConstraintValidator;

import pl.softmil.validator.conditional.constraint.FieldNotBlankIf;

public class FieldNotBlankIfValidator extends IfValidator implements
        ConstraintValidator<FieldNotBlankIf, Object> {
    private String expressionString;

    private String field;

    private String message;

    @Override
    public void initialize(FieldNotBlankIf constraintAnnotation) {
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
        boolean result = false;
        String propertyAsString = (String) propertyValue;
        if (propertyAsString != null && propertyAsString.trim().length() > 0) {
            result = true;
        }
        return result;
    }

}
