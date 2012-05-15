package pl.softmil.validator.amount;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.softmil.test.integration.utils.validation.ConstraintViolationHelper;
import pl.softmil.test.integration.utils.validation.ErrorMatcher.ViolationContextContainer;

@ContextConfiguration("/validators-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class AmountValidatorTest {
    @Autowired
    private Validator validator;

    @Test
    public void testForNegative() throws Exception {
        AmountExampleBean amountExampleBean = new AmountExampleBean(
                new BigDecimal(-22));

        Set<ConstraintViolation<AmountExampleBean>> validate = validator
                .validate(amountExampleBean);
        ConstraintViolationHelper constraintViolationHelper = ConstraintViolationHelper
                .build(validate);

        constraintViolationHelper.assertAnyViolationsRaised();
        constraintViolationHelper
                .assertSpecificViolationRaised(new ViolationContextContainer(
                        "monthlyRate", amountExampleBean.getMonthlyRate(),
                        "{javax.validation.constraints.DecimalMin.message}"));
    }

    @Test
    public void testForFraction() throws Exception {
        AmountExampleBean amountExampleBean = new AmountExampleBean(
                new BigDecimal("22.5"));

        Set<ConstraintViolation<AmountExampleBean>> validate = validator
                .validate(amountExampleBean);
        ConstraintViolationHelper constraintViolationHelper = ConstraintViolationHelper
                .build(validate);

        constraintViolationHelper.assertAnyViolationsRaised();
        constraintViolationHelper
                .assertSpecificViolationRaised(new ViolationContextContainer(
                        "monthlyRate", amountExampleBean.getMonthlyRate(),
                        "{invalid fraction amount}"));
    }

    @Test
    public void testForTooBigDecimalValue() throws Exception {
        AmountExampleBean amountExampleBean = new AmountExampleBean(
                new BigDecimal(123456789));

        Set<ConstraintViolation<AmountExampleBean>> validate = validator
                .validate(amountExampleBean);
        ConstraintViolationHelper constraintViolationHelper = ConstraintViolationHelper
                .build(validate);

        constraintViolationHelper.assertAnyViolationsRaised();
        constraintViolationHelper
                .assertSpecificViolationRaised(new ViolationContextContainer(
                        "monthlyRate", amountExampleBean.getMonthlyRate(),
                        "{invalid fraction amount}"));
    }

    @Test
    public void testforValidValue() throws Exception {
        AmountExampleBean amountExampleBean = new AmountExampleBean(
                new BigDecimal(300));

        Set<ConstraintViolation<AmountExampleBean>> validate = validator
                .validate(amountExampleBean);
        ConstraintViolationHelper constraintViolationHelper = ConstraintViolationHelper
                .build(validate);

        constraintViolationHelper.assertNoViolationsRaised();
    }

}
