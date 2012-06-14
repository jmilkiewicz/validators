package pl.softmil.validator.pesel.impl;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import javax.validation.ConstraintValidatorContext;

import org.junit.Test;
import org.mockito.Mockito;

public class PeselValidatorTest {
    private PeselValidator sut = new PeselValidator();
    private ConstraintValidatorContext constraintValidatorContext = Mockito
            .mock(ConstraintValidatorContext.class);

    @Test
    public void testValidForOwnPesel() {
        String myPesel = "81092706995";
        boolean valid = sut.isValid(myPesel, constraintValidatorContext);

        assertThat("pesel: " + myPesel + " must be valid, i am using it!",
                valid, is(true));
    }
    
    @Test
    public void testInValidForTooShort() {
        String pesel = "8109270699";
        boolean valid = sut.isValid(pesel, constraintValidatorContext);

        assertThat("pesel: " + pesel + " can't be valid since it is too short",
                valid, is(false));
    }
    
    @Test
    public void testInValidForIncorrectPesel() {
        String pesel = "48040501580";
        boolean valid = sut.isValid(pesel, constraintValidatorContext);

        assertThat("pesel: " + pesel + " must not be valid",
                valid, is(false));
    }
    
    @Test
    public void testValidForNull() {
        boolean valid = sut.isValid(null, constraintValidatorContext);

        assertThat("pesel: must be valid for null",
                valid, is(true));
    }
    
    @Test
    public void testInValidForNull() {
        boolean valid = sut.isValid(null, constraintValidatorContext);

        assertThat("pesel: must be valid for null",
                valid, is(true));
    }

}
