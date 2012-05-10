package pl.softmil.validator.condition.ifblank;

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
public class FieldNotBlankIfValidatorTest {
    @Autowired
    private Validator validator;

    @Test
    public void testWhenFieldIsNullButMustNotBeBlank() {
        IfBlankBean ifBlankBean = new IfBlankBean(null, MyEnum.RAZ);
        ConstraintViolationHelper constraintViolationHelper = validate(ifBlankBean);
        constraintViolationHelper.assertAnyViolationsRaised();
        constraintViolationHelper
                .assertSpecificViolationRaised(new ViolationContextContainer(
                        "cos", ifBlankBean,
                        "{org.hibernate.validator.constraints.NotBlank.message}"));
    }

    @Test
    public void testWhenFieldIsBlankButMustNotBeBlank() {
        IfBlankBean ifBlankBean = new IfBlankBean("     ", MyEnum.RAZ);
        ConstraintViolationHelper constraintViolationHelper = validate(ifBlankBean);
        constraintViolationHelper.assertAnyViolationsRaised();
        constraintViolationHelper
                .assertSpecificViolationRaised(new ViolationContextContainer(
                        "cos", ifBlankBean,
                        "{org.hibernate.validator.constraints.NotBlank.message}"));
    }

    
    @Test
    public void testWhenFieldIsNullButCanBeBlank() {
        IfBlankBean ifBlankBean = new IfBlankBean(null, MyEnum.DWA);
        ConstraintViolationHelper constraintViolationHelper = validate(ifBlankBean);
        constraintViolationHelper.assertNoViolationsRaised();
    }
    
    @Test
    public void testWhenFieldIsNotBlankAndMustNotBeBlank() {
        IfBlankBean ifBlankBean = new IfBlankBean("a", MyEnum.DWA);
        ConstraintViolationHelper constraintViolationHelper = validate(ifBlankBean);
        constraintViolationHelper.assertNoViolationsRaised();
    }
    
    @Test
    public void testWhenFieldIsNotBlankButCanBeBlank() {
        IfBlankBean ifNullBean = new IfBlankBean("D", MyEnum.DWA);
        ConstraintViolationHelper constraintViolationHelper = validate(ifNullBean);
        constraintViolationHelper.assertNoViolationsRaised();
    }

    private ConstraintViolationHelper validate(IfBlankBean ifNullBean) {
        Set<ConstraintViolation<IfBlankBean>> validate = validator
                .validate(ifNullBean);
        return ConstraintViolationHelper.build(validate);
    }

}
