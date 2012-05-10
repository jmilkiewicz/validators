package pl.softmil.validator.condition.ifnull;

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
import pl.softmil.validator.condition.MyEnum;

@ContextConfiguration("/validators.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class FieldNotNullIfValidatorTest {
    @Autowired
    private Validator validator;

    @Test
    public void testWhenFieldIsNullButMustNotBeNull() {
        IfNullBean ifNullBean = new IfNullBean(null, MyEnum.RAZ);
        ConstraintViolationHelper constraintViolationHelper = validate(ifNullBean);
        constraintViolationHelper.assertAnyViolationsRaised();
        constraintViolationHelper
                .assertSpecificViolationRaised(new ViolationContextContainer(
                        "cos", ifNullBean,
                        "{javax.validation.constraints.NotNull.message}"));
    }

    @Test
    public void testWhenFieldIsNullButCanBeNull() {
        IfNullBean ifNullBean = new IfNullBean(null, MyEnum.DWA);
        ConstraintViolationHelper constraintViolationHelper = validate(ifNullBean);
        constraintViolationHelper.assertNoViolationsRaised();
    }
    
    @Test
    public void testWhenFieldIsNotNullAndMustNotBeNull() {
        IfNullBean ifNullBean = new IfNullBean(Boolean.TRUE, MyEnum.DWA);
        ConstraintViolationHelper constraintViolationHelper = validate(ifNullBean);
        constraintViolationHelper.assertNoViolationsRaised();
    }
    
    @Test
    public void testWhenFieldIsNotNullButCanBeNull() {
        IfNullBean ifNullBean = new IfNullBean(Boolean.FALSE, MyEnum.DWA);
        ConstraintViolationHelper constraintViolationHelper = validate(ifNullBean);
        constraintViolationHelper.assertNoViolationsRaised();
    }

    private ConstraintViolationHelper validate(IfNullBean ifNullBean) {
        Set<ConstraintViolation<IfNullBean>> validate = validator
                .validate(ifNullBean);
        return ConstraintViolationHelper.build(validate);
    }

}
