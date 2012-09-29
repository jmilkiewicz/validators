package pl.softmil.validator.nip.impl;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import pl.softmil.validator.nip.constraint.Nip;

public class NipValidator implements ConstraintValidator<Nip, String> {
	private static final String NO_HYPHENS_NIP_FORMAT = "^[0-9]{10}$";
	private static final String VAT_UE_PL_NIP_FORMAT = "^PL ?[0-9]{10}$";
	private static final String PERSON_NIP_FORMAT = "^[0-9]{3}-[0-9]{3}-[0-9]{2}-[0-9]{2}$";
    private static final String LEGAL_NIP_FORMAT = "^[0-9]{3}-[0-9]{2}-[0-9]{2}-[0-9]{3}$";
	private static final int[] CHECKSUM_WEIGHTS = { 6, 5, 7, 2, 3, 4, 5, 6, 7 };

	@Override
	public void initialize(Nip constraintAnnotation) {
	}

	public boolean isValid(String nip, ConstraintValidatorContext context) {
		if (nip == null)
			return true;
		return isFormatValid(nip) && isInlandRevenueCodeValid(nip)
				&& isControlDigitValid(nip);
	}

	private boolean isFormatValid(String nip) {
		return isNoHyphensNIP(nip) || isLegalNipFormat(nip) || isPersonNipFormat(nip) || isVATUEPolishNIP(nip);
	}

	private boolean isNoHyphensNIP(String nip) {
		return Pattern.matches(NO_HYPHENS_NIP_FORMAT, nip);
	}
	
	private boolean isLegalNipFormat(String nip) {
		return Pattern.matches(LEGAL_NIP_FORMAT, nip);
	}
	
	private boolean isPersonNipFormat(String nip) {
		return Pattern.matches(PERSON_NIP_FORMAT, nip);
	}

	private boolean isVATUEPolishNIP(String nip) {
		return Pattern.matches(VAT_UE_PL_NIP_FORMAT, nip);
	}

	private boolean isInlandRevenueCodeValid(String nip) {
		return nip.charAt(0) != '0' && nip.charAt(2) != '0';
	}

	private boolean isControlDigitValid(String nip) {
		boolean result = false;
		String calculateControlDigit = String
				.valueOf(calculateChecksum(nip) % 11);
		if (calculateControlDigit.length() == 1) {
			result = nip.endsWith(calculateControlDigit);
		}
		return result;
	}

	private int calculateChecksum(String nip) {
		String noPrefixNIP = nip.replaceAll("PL", "").trim();
		String[] digits = noPrefixNIP.replace("-", "").split("");
		int sum = 0;
		for (int i = 0; i < CHECKSUM_WEIGHTS.length; i++)
			sum += Integer.parseInt(digits[i + 1]) * CHECKSUM_WEIGHTS[i];
		return sum;
	}

}
