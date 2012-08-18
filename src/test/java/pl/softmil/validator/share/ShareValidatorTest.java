package pl.softmil.validator.share;

import java.util.Set;

import javax.validation.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.softmil.test.integration.utils.validation.*;
import pl.softmil.test.integration.utils.validation.ErrorMatcher.ViolationContextContainer;

@ContextConfiguration("/validators-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ShareValidatorTest {
    @Autowired
    private Validator validator;
    
    private ShareExampleBean sut = new ShareExampleBean();
    
    @Test
    public void validationErrorIfShareLessThenZero() throws Exception {
        sut.setShare("-1");
        
        ConstraintViolationHelper constraintViolationHelper = validate();

        constraintViolationHelper.assertAnyViolationsRaised();
        constraintViolationHelper
                .assertSpecificViolationRaised(new ViolationContextContainer(
                        "share", sut.getShare(),
                        "{javax.validation.constraints.DecimalMin.message}"));
    }
    
    @Test
    public void validationErrorIfShareIsGreateThen100() throws Exception {
        sut.setShare("101");
        
        ConstraintViolationHelper constraintViolationHelper = validate();

        constraintViolationHelper.assertAnyViolationsRaised();
        constraintViolationHelper
                .assertSpecificViolationRaised(new ViolationContextContainer(
                        "share", sut.getShare(),
                        "{javax.validation.constraints.DecimalMax.message}"));
    }
    
    @Test
    public void noValidationErrorsIfShareIs100() throws Exception {
        sut.setShare("100");
        
        ConstraintViolationHelper constraintViolationHelper = validate();

        constraintViolationHelper.assertNoViolationsRaised();
    }
    
    @Test
    public void noValidationErrorsIfShareIsValidPercentage() throws Exception {
        sut.setShare("79");
        
        ConstraintViolationHelper constraintViolationHelper = validate();

        constraintViolationHelper.assertNoViolationsRaised();
    }
    
    @Test
    public void validationErrorIfShareIsZero() throws Exception {
        sut.setShare("0");
        
        ConstraintViolationHelper constraintViolationHelper = validate();

        constraintViolationHelper.assertAnyViolationsRaised();
        constraintViolationHelper
                .assertSpecificViolationRaised(new ViolationContextContainer(
                        "share", sut.getShare(),
                        "{javax.validation.constraints.DecimalMin.message}"));
    }
    
    @Test
    public void validationErrorIfShareIsNotInteger() throws Exception {
        sut.setShare("12.4");
        
        ConstraintViolationHelper constraintViolationHelper = validate();

        constraintViolationHelper.assertAnyViolationsRaised();
        constraintViolationHelper
                .assertSpecificViolationRaised(new ViolationContextContainer(
                        "share", sut.getShare(),
                        "{javax.validation.constraints.Integer}"));
    }

    private ConstraintViolationHelper validate() {
        Set<ConstraintViolation<ShareExampleBean>> validate = validator
                .validate(sut);
        return ConstraintViolationHelper
                .build(validate);
    }
}
