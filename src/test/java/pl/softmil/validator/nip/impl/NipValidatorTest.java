package pl.softmil.validator.nip.impl;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import javax.validation.ConstraintValidatorContext;

import org.junit.Test;
import org.mockito.Mockito;

public class NipValidatorTest {
	private NipValidator sut = new NipValidator();
	private ConstraintValidatorContext constraintValidatorContext = Mockito
			.mock(ConstraintValidatorContext.class);

	@Test
	public void testForNullNip() {
		boolean valid = sut.isValid(null, constraintValidatorContext);

		assertThat("null nip is valid!", valid, is(true));
	}

	@Test
	public void testForMyOwnNip() {
		boolean valid = sut.isValid("7851665795", constraintValidatorContext);

		assertThat("my own nip is valid", valid, is(true));
	}

	@Test
	public void testForWikipediaValidNip() {
		String nip = "123-456-32-18";
		boolean valid = sut.isValid(nip, constraintValidatorContext);

		assertThat("wikipedia valid nip " + nip + "  must be valid", valid,
				is(true));
	}

	@Test
	public void testForWikipediaInValidNip() {
		String nip = "1234567890";
		boolean valid = sut.isValid(nip, constraintValidatorContext);

		assertThat("wikipedia invalid nip " + nip + "  must be invalid", valid,
				is(false));
	}

	@Test
	public void testForOwnNipWithHyphens() {
		String nip = "785-166-57-95";
		boolean valid = sut.isValid(nip, constraintValidatorContext);

		assertThat("my own nip" + nip + "  with hyphens is valid", valid,
				is(true));
	}

	@Test
	public void testForTooLongNip() {
		String nipToCheck = "78516657951";
		boolean valid = sut.isValid(nipToCheck, constraintValidatorContext);

		assertThat(nipToCheck + " can not be valid since it is too long",
				valid, is(false));
	}

	@Test
	public void testForTooShortNip() {
		String nipToCheck = "785166579";
		boolean valid = sut.isValid(nipToCheck, constraintValidatorContext);

		assertThat(nipToCheck + " can not be valid since it is too short",
				valid, is(false));
	}

}
