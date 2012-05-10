package pl.softmil.validator.conditional.impl;

import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;

public abstract class IfValidator {
    @Autowired
    private ExpressionParser expressionParser;

    protected abstract String getExpressionString();

    protected abstract String getField();

    protected abstract String getMessage();

    protected abstract boolean isPropertyValid(Object propertyValue);

    public boolean isValid(Object bean, ConstraintValidatorContext context) {
        boolean result = true;
        if (isExpressionTrue(bean)) {
            boolean isValueValid = isValueValid(bean);
            if (!isValueValid) {
                result = false;
                addErrors(context);
            }
        }
        return result;
    }

    private void addErrors(ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(getMessage())
                .addNode(getField()).addConstraintViolation();
        // context.buildConstraintViolationWithTemplate("As").addNode("name").addNode("name").inIterable().addConstraintViolation();
    }

    private boolean isValueValid(Object bean) {
        Object propertyValue = getPropertyValue(bean);
        return isPropertyValid(propertyValue);

    }

    private boolean isExpressionTrue(Object value) {
        Expression expression = expressionParser
                .parseExpression(getExpressionString());
        return Boolean.TRUE.equals(expression.getValue(value, Boolean.class));
    }

    private Object getPropertyValue(Object bean) {
        try {
            return PropertyUtils.getProperty(bean, getField());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
