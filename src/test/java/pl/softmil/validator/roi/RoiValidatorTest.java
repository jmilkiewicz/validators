package pl.softmil.validator.roi;

import static org.hamcrest.Matchers.*;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.softmil.test.integration.utils.validation.ConstraintViolationHelper;

@ContextConfiguration("/validators-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class RoiValidatorTest {
    @Autowired
    private Validator validator;

    @Test
    public void testValidationSuccessForNull() throws Exception {
        RoiExampleBean roiExampleBean = new RoiExampleBean(null);
        Set<ConstraintViolation<RoiExampleBean>> validate = validator.validate(roiExampleBean);
        
        ConstraintViolationHelper constraintViolationHelper = ConstraintViolationHelper
                .build(validate);
        
        constraintViolationHelper.assertNoViolationsRaised();
    }
    
    @Test
    public void testValidationSuccessForSmallInteger() throws Exception {
        RoiExampleBean roiExampleBean = new RoiExampleBean(new BigDecimal("6"));
        Set<ConstraintViolation<RoiExampleBean>> validate = validator.validate(roiExampleBean);
        
        ConstraintViolationHelper constraintViolationHelper = ConstraintViolationHelper
                .build(validate);
        
        constraintViolationHelper.assertNoViolationsRaised();
    }
    
    @Test
    public void testValidationSuccessForSmallDecimal() throws Exception {
        RoiExampleBean roiExampleBean = new RoiExampleBean(new BigDecimal("9.9"));
        Set<ConstraintViolation<RoiExampleBean>> validate = validator.validate(roiExampleBean);
        
        ConstraintViolationHelper constraintViolationHelper = ConstraintViolationHelper
                .build(validate);
        
        constraintViolationHelper.assertNoViolationsRaised();
    }
    
    @Test
    public void testValidationFailWith2ErrorsForBelowZeroAndInvalidFraction() throws Exception {
        RoiExampleBean roiExampleBean = new RoiExampleBean(new BigDecimal("-1212.323"));
        Set<ConstraintViolation<RoiExampleBean>> validate = validator.validate(roiExampleBean);
        
        ConstraintViolationHelper constraintViolationHelper = ConstraintViolationHelper
                .build(validate);
        
        constraintViolationHelper.assertGivenNumberViolationsRaised(equalTo(2));
    }
    
    @Test
    public void testValidationFailForInvalidFractionZero() throws Exception {
        RoiExampleBean roiExampleBean = new RoiExampleBean(new BigDecimal("12.32"));
        Set<ConstraintViolation<RoiExampleBean>> validate = validator.validate(roiExampleBean);
        
        ConstraintViolationHelper constraintViolationHelper = ConstraintViolationHelper
                .build(validate);
        
        constraintViolationHelper.assertGivenNumberViolationsRaised(equalTo(1));
    }
    
    @Test
    public void testValidationFailForZero() throws Exception {
        RoiExampleBean roiExampleBean = new RoiExampleBean(new BigDecimal("0"));
        Set<ConstraintViolation<RoiExampleBean>> validate = validator.validate(roiExampleBean);
        
        ConstraintViolationHelper constraintViolationHelper = ConstraintViolationHelper
                .build(validate);
        
        constraintViolationHelper.assertGivenNumberViolationsRaised(equalTo(1));
    }

    
    @Test
    public void testValidationFailTooLargetValue() throws Exception {
        RoiExampleBean roiExampleBean = new RoiExampleBean(new BigDecimal("100"));
        Set<ConstraintViolation<RoiExampleBean>> validate = validator.validate(roiExampleBean);
        
        ConstraintViolationHelper constraintViolationHelper = ConstraintViolationHelper
                .build(validate);
        
        constraintViolationHelper.assertGivenNumberViolationsRaised(equalTo(1));
    }

}
