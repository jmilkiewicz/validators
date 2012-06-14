package pl.softmil.validator.pesel.impl;

import javax.validation.*;

import pl.softmil.validator.pesel.constraint.Pesel;

public class PeselValidator implements ConstraintValidator<Pesel, String> {

    @Override
    public void initialize(Pesel constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null){
            return true;
        }
        int psize = value.length();
        if (psize != 11) {
            return false;
        }
        int[] weights = { 1, 3, 7, 9, 1, 3, 7, 9, 1, 3 };
        int j = 0, sum = 0, control = 0;
        int csum = new Integer(value.substring(psize - 1)).intValue();
        for (int i = 0; i < psize - 1; i++) {
            char c = value.charAt(i);
            j = new Integer(String.valueOf(c)).intValue();
            sum += j * weights[i];
        }
        control = 10 - (sum % 10);
        if (control == 10) {
            control = 0;
        }
        return (control == csum);
    }

}
