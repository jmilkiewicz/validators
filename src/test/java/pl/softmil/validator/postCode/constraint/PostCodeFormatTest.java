package pl.softmil.validator.postCode.constraint;

import javax.validation.Validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.softmil.test.integration.utils.validation.ConstraintViolationHelper;

@ContextConfiguration("/validators-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class PostCodeFormatTest {
    @Autowired
    private Validator validator;
    
    private static class SampleBean{
        @PostCodeFormat
        private final String postCode;
        
        public SampleBean(String postCode) {
            super();
            this.postCode = postCode;
        }
    }
    
    @Test
    public void testForValidPostCode() {
        SampleBean sampleBean = new SampleBean("66-213");
        ConstraintViolationHelper helper = ConstraintViolationHelper.build(validator.validate(sampleBean));
        
        helper.assertNoViolationsRaised();
    }
    
    @Test
    public void testForInValidPostCode() {
        SampleBean sampleBean = new SampleBean("0-313");
        ConstraintViolationHelper helper = ConstraintViolationHelper.build(validator.validate(sampleBean));
        
        helper.assertViolationForGivenPropertyRaised("postCode");
    }

}
