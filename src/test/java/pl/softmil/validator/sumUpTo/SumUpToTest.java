package pl.softmil.validator.sumUpTo;

import java.math.BigDecimal;

import javax.validation.Validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.softmil.test.integration.utils.validation.ConstraintViolationHelper;
import pl.softmil.validator.sumUpTo.impl.AsBigDecimal;

@ContextConfiguration("/validators-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SumUpToTest {
    @Autowired
    private Validator validator;
    
    private Container sut = new Container();
    @Test
    public void testDoesNotSumForEmptyCollection() {
        ConstraintViolationHelper validationHelper = validate();
        
        validationHelper.assertAnyViolationsRaised();
        validationHelper.assertViolationForGivenPropertyRaised("elems");
    }
    
    
    @Test
    public void testSums() {
        addToContainer(asBigDecima("10.5"));
        addToContainer(asBigDecima("60"));
        addToContainer(asBigDecima("29.5"));
        
        
        ConstraintViolationHelper validationHelper = validate();
        
        validationHelper.assertNoViolationsRaised();
    }
    
    
    private void addToContainer(AsBigDecimal asBigDecimal) {
        sut.getElems().add(asBigDecimal);
    }

    private AsBigDecimal asBigDecima(final String value){
        return new AsBigDecimal() {
            @Override
            public BigDecimal asBigDecimal() {
                return new BigDecimal(value);
            }
        };
    }
    
    private ConstraintViolationHelper validate() {
        return ConstraintViolationHelper.build(validator.validate(sut));
    }

}
