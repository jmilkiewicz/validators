package pl.softmil.validator.sumUpTo;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import javax.validation.Validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
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
    
    @Test
    public void co(){
     // Create a configuration:  auto-grow null fields and empty collections.
        SpelParserConfiguration configuration = new SpelParserConfiguration(true, true);

        // Create a parser.
        SpelExpressionParser parser = new SpelExpressionParser(configuration);

        
        
        
        
        // Parse an expression.
        Expression expression = parser.parseExpression(" a < (b == null? new Integer(100) : b)");

        // Create an evaluation context.
        StandardEvaluationContext context = new StandardEvaluationContext();

        // Expose variables to the script.
        AB rootObject = new AB();
        context.setRootObject(rootObject);
        //context.setVariable("b", "6");

        // Evaluate the expression.
        Boolean value = expression.getValue(context, Boolean.class);
        
        assertThat(value, is(true));

        

        
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
