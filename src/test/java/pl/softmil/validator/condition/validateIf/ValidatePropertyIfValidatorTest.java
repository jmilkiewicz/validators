package pl.softmil.validator.condition.validateIf;

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
public class ValidatePropertyIfValidatorTest {
    @Autowired
    private Validator validator;
    
    @Test
    public void shouldValidatePropertyWhenExpressionTrue() {
        MyBean myBean = new MyBean();
        myBean.setId(33l);
        myBean.setShare(null);
        
        Set<ConstraintViolation<MyBean>> validate = validator.validate(myBean);
        ConstraintViolationHelper constraintViolationHelper = ConstraintViolationHelper.build(validate);
        constraintViolationHelper.assertViolationForGivenPropertyRaised("share");
    }
    
    @Test
    public void shouldNotValidatePropertyWhenExpressionFalse() {
        MyBean myBean = new MyBean();
        myBean.setId(null);
        myBean.setShare(null);
        
        Set<ConstraintViolation<MyBean>> validate = validator.validate(myBean);
        ConstraintViolationHelper constraintViolationHelper = ConstraintViolationHelper.build(validate);
        constraintViolationHelper.assertNoViolationsRaised();
    }

}
