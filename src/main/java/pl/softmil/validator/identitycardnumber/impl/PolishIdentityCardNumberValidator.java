package pl.softmil.validator.identitycardnumber.impl;

import java.util.*;

import javax.validation.*;

import pl.softmil.validator.identitycardnumber.constraint.PolishIdentityCardNumber;

public class PolishIdentityCardNumberValidator implements
        ConstraintValidator<PolishIdentityCardNumber, String> {
    private static java.util.regex.Pattern POLISH_IDENTITY_CARD_NUMBER_PATTERN = java.util.regex.Pattern
            .compile("[a-zA-Z]{3}[0-9]{6}");
    private static Map<Character, Integer> mapping = new HashMap<Character, Integer>();
    static {
        mapping.put('A', 10);
        mapping.put('B', 11);
        mapping.put('C', 12);
        mapping.put('D', 13);
        mapping.put('E', 14);
        mapping.put('F', 15);
        mapping.put('G', 16);
        mapping.put('H', 17);
        mapping.put('I', 18);
        mapping.put('J', 19);
        mapping.put('K', 20);
        mapping.put('L', 21);
        mapping.put('M', 22);
        mapping.put('N', 23);
        mapping.put('O', 24);
        mapping.put('P', 25);
        mapping.put('Q', 26);
        mapping.put('R', 27);
        mapping.put('S', 28);
        mapping.put('T', 29);
        mapping.put('U', 30);
        mapping.put('V', 31);
        mapping.put('W', 32);
        mapping.put('X', 33);
        mapping.put('Y', 34);
        mapping.put('Z', 35);
    }

    private static Integer[] MULTIPLICANDS = new Integer[] { 7, 3, 1, 9, 7, 3,
            1, 7, 3 };

    @Override
    public void initialize(PolishIdentityCardNumber constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return validate(value, context);
    }

    private boolean validate(String value, ConstraintValidatorContext context) {
        boolean result = true;
        boolean matchesPattern = matchesPattern(value);
        if (matchesPattern) {
            result = validateAgainsCheckSum(value);
        } else {
            result = false;
            addPatternContstraintViolation(context);
        }
        return result;
    }

    private void addPatternContstraintViolation(
            ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(
                "polishdocumentnumber.invalid.format").addConstraintViolation();
    }

    private boolean matchesPattern(String value) {
        return POLISH_IDENTITY_CARD_NUMBER_PATTERN.matcher(value).matches();
    }

    private boolean validateAgainsCheckSum(String value) {
        Integer[] valuesFromLetters = getValuesFromLetters(value
                .substring(0, 3));
        Integer[] digits = convertDigitsToNumber(value.substring(3));
        Integer[] mergeResult = merge(valuesFromLetters, digits);
        Integer[] multiplyValues = multiply(mergeResult);
        int sumupValue = sumupValues(multiplyValues);
        return sumupValue % 10 == 0;
    }

    private int sumupValues(Integer[] values) {
        int sum = 0;
        for (Integer integer : values) {
            sum += integer;
        }
        return sum;
    }

    private Integer[] multiply(Integer[] values) {
        Integer[] result = new Integer[9];
        for (int i = 0; i < values.length; i++) {
            result[i] = values[i].intValue() * MULTIPLICANDS[i].intValue();
        }
        return result;
    }

    private Integer[] merge(Integer[] valuesFromLetters, Integer[] digits) {
        Integer[] result = new Integer[9];
        for (int i = 0; i < 3; i++) {
            result[i] = valuesFromLetters[i];
        }
        for (int i = 0; i < digits.length; i++) {
            result[i + 3] = digits[i];
        }
        return result;
    }

    private Integer[] convertDigitsToNumber(String substring) {
        Integer[] result = new Integer[6];
        for (int i = 0; i < substring.length(); i++) {
            result[i] = (int) substring.charAt(i);
        }
        return result;
    }

    private Integer[] getValuesFromLetters(String letters) {
        Integer[] result = new Integer[3];
        for (int i = 0; i < letters.length(); i++) {
            result[i] = resolveToNumber(Character.valueOf(letters.charAt(i)));
        }
        return result;
    }

    private Integer resolveToNumber(Character character) {
        return mapping.get(Character.toUpperCase(character));
    }
}
