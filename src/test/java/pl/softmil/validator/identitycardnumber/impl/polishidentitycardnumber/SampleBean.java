package pl.softmil.validator.identitycardnumber.impl.polishidentitycardnumber;

import lombok.Data;
import pl.softmil.validator.identitycardnumber.constraint.PolishIdentityCardNumber;

@Data
public class SampleBean {
    @PolishIdentityCardNumber
    private String document;
}
