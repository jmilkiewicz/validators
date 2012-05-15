package pl.softmil.validator.el;

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
public class ElValidatorTest {
    @Autowired
    private Validator validator;

    @Test
    public void testWhenPropertiesMismatch() {
        PropertyPropertyRepeatBean propertyPropertyRepeatBean = new PropertyPropertyRepeatBean(
                "prop", "prop1");

        ConstraintViolationHelper constraintViolationHelper = validate(propertyPropertyRepeatBean);

        constraintViolationHelper.assertAnyViolationsRaised();
        constraintViolationHelper.assertGivenNumberViolationsRaised(2);
        constraintViolationHelper
                .assertSpecificViolationRaised(new ViolationContextContainer(
                        "prop", propertyPropertyRepeatBean,
                        "{password.passwordRepeat.mismatch}"));
        constraintViolationHelper
                .assertSpecificViolationRaised(new ViolationContextContainer(
                        "propRepeat", propertyPropertyRepeatBean,
                        "{password.passwordRepeat.mismatch}"));
    }

    @Test
    public void testWhenPropertiesMatch() {
        PropertyPropertyRepeatBean propertyPropertyRepeatBean = new PropertyPropertyRepeatBean(
                "prop", "prop");

        ConstraintViolationHelper constraintViolationHelper = validate(propertyPropertyRepeatBean);

        constraintViolationHelper.assertNoViolationsRaised();
    }

    private ConstraintViolationHelper validate(
            PropertyPropertyRepeatBean propertyPropertyRepeatBean) {
        Set<ConstraintViolation<PropertyPropertyRepeatBean>> validate = validator
                .validate(propertyPropertyRepeatBean);

        ConstraintViolationHelper constraintViolationHelper = ConstraintViolationHelper
                .build(validate);
        return constraintViolationHelper;
    }

}
