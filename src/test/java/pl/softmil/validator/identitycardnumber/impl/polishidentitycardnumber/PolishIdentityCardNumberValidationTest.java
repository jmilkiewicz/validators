package pl.softmil.validator.identitycardnumber.impl.polishidentitycardnumber;

import static org.hamcrest.Matchers.equalTo;

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
public class PolishIdentityCardNumberValidationTest {
    @Autowired
    private Validator validator;

    private SampleBean sampleBean = new SampleBean();

    @Test
    public void testForInvalidPatternTooFewLetters() {
        sampleBean.setDocument("sc123456");

        ConstraintViolationHelper validationResult = validate();

        assertInvalidFormat(validationResult);
    }
    
    @Test
    public void testForInvalidPatternToManyLetters() {
        sampleBean.setDocument("aasc123456");

        ConstraintViolationHelper validationResult = validate();

        assertInvalidFormat(validationResult);
    }
    
    @Test
    public void testForInvalidPatternTooManyDigits() {
        sampleBean.setDocument("ssc1234562");

        ConstraintViolationHelper validationResult = validate();

        assertInvalidFormat(validationResult);
    }

    @Test
    public void testForIdwithWhiteSpaces() {
        sampleBean.setDocument("   ASM127929   ");

        ConstraintViolationHelper validationResult = validate();

        assertInvalidFormat(validationResult);
    }
    
    @Test
    public void testForMyId() {
        sampleBean.setDocument("ASM127929");

        ConstraintViolationHelper violations = validate();
        
        violations.assertNoViolationsRaised();
    }
    
    @Test
    public void testForWikipediaSampleId() {
        sampleBean.setDocument("ABA300000");

        ConstraintViolationHelper violations = validate();
        
        violations.assertNoViolationsRaised();
    }
    
    @Test
    public void testForMyIdLowerCase() {
        sampleBean.setDocument("asm127929");

        ConstraintViolationHelper violations = validate();
        
        violations.assertNoViolationsRaised();
    }
    
    @Test
    public void testForInvalidChecksum() {
        sampleBean.setDocument("ASM127919");

        ConstraintViolationHelper violations = validate();
        
        assertInvalidCheckSum(violations);
    }
    
    private void assertInvalidCheckSum(ConstraintViolationHelper violations) {
        assertSingleViolationForDocumentProperty(violations);
        violations.assertSpecificViolationRaised(new ViolationContextContainer(
                "document", sampleBean.getDocument(),
                "{polishdocumentnumber.invalid.checksum}"));
    }

    private ConstraintViolationHelper validate() {
        Set<ConstraintViolation<SampleBean>> violations = validator
                .validate(sampleBean);
        return ConstraintViolationHelper
                .build(violations);
    }


    @Test
    public void testForEmptyString() {
        sampleBean.setDocument("");

        ConstraintViolationHelper violations = validate();

        assertInvalidFormat(violations);
    }

    private void assertInvalidFormat(
            ConstraintViolationHelper validationResult) {
        assertSingleViolationForDocumentProperty(validationResult);
        validationResult.assertSpecificViolationRaised(new ViolationContextContainer(
                "document", sampleBean.getDocument(),
                "polishdocumentnumber.invalid.format"));
    }

    private void assertSingleViolationForDocumentProperty(
            ConstraintViolationHelper helper) {
        helper.assertViolationForGivenPropertyRaised("document");
        helper.assertGivenNumberViolationsRaised(equalTo(1));
    }

}
