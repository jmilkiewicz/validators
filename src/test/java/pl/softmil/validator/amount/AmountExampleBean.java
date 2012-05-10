package pl.softmil.validator.amount;

import java.math.BigDecimal;

import pl.softmil.validator.amount.constraint.Amount;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class AmountExampleBean {
    @Amount    
    private final BigDecimal monthlyRate;       

}
